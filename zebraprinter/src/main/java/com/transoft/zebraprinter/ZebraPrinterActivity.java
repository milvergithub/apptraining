package com.transoft.zebraprinter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.zebra.sdk.comm.BluetoothConnection;
import com.zebra.sdk.comm.Connection;
import com.zebra.sdk.comm.ConnectionException;
import com.zebra.sdk.comm.TcpConnection;
import com.zebra.sdk.printer.PrinterLanguage;
import com.zebra.sdk.printer.PrinterStatus;
import com.zebra.sdk.printer.SGD;
import com.zebra.sdk.printer.ZebraPrinter;
import com.zebra.sdk.printer.ZebraPrinterFactory;
import com.zebra.sdk.printer.ZebraPrinterLanguageUnknownException;
import com.zebra.sdk.printer.ZebraPrinterLinkOs;

public class ZebraPrinterActivity extends Activity {

  private Connection connection;

  private RadioButton btRadioButton;
  private EditText macAddressEditText;
  private EditText ipAddressEditText;
  private EditText portNumberEditText;
  private static final String bluetoothAddressKey = "ZEBRA_DEMO_BLUETOOTH_ADDRESS";
  private static final String tcpAddressKey = "ZEBRA_DEMO_TCP_ADDRESS";
  private static final String tcpPortKey = "ZEBRA_DEMO_TCP_PORT";
  private static final String PREFS_NAME = "OurSavedAddress";

  private Button testButton;
  private ZebraPrinter printer;
  private TextView statusField;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.zebra_printer);

    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

    ipAddressEditText = this.findViewById(R.id.ipAddressInput);
    String ip = settings.getString(tcpAddressKey, "");
    ipAddressEditText.setText(ip);

    portNumberEditText = this.findViewById(R.id.portInput);
    String port = settings.getString(tcpPortKey, "");
    portNumberEditText.setText(port);

    macAddressEditText = this.findViewById(R.id.macInput);
    String mac = settings.getString(bluetoothAddressKey, "");
    macAddressEditText.setText(mac);

    TextView t2 = findViewById(R.id.launchpad_link);
    t2.setMovementMethod(LinkMovementMethod.getInstance());

    statusField = this.findViewById(R.id.statusText);


    btRadioButton = this.findViewById(R.id.bluetoothRadio);


    RadioGroup radioGroup = this.findViewById(R.id.radioGroup);
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.bluetoothRadio) {
          toggleEditField(macAddressEditText, true);
          toggleEditField(portNumberEditText, false);
          toggleEditField(ipAddressEditText, false);
        } else {
          toggleEditField(portNumberEditText, true);
          toggleEditField(ipAddressEditText, true);
          toggleEditField(macAddressEditText, false);
        }
      }
    });
    testButton = this.findViewById(R.id.testButton);
    testButton.setOnClickListener(new View.OnClickListener() {

      public void onClick(View v) {
        new Thread(new Runnable() {
          public void run() {
            enableTestButton(false);
            Looper.prepare();
            doConnectionTest();
            Looper.loop();
            Looper.myLooper().quit();
          }
        }).start();
      }
    });
  }

  public ZebraPrinter connect() {
    setStatus("Connecting...", Color.YELLOW);
    connection = null;
    if (isBluetoothSelected()) {
      connection = new BluetoothConnection(getMacAddressFieldText());
      SettingsHelper.saveBluetoothAddress(this, getMacAddressFieldText());
    } else {
      try {
        int port = Integer.parseInt(getTcpPortNumber());
        connection = new TcpConnection(getTcpAddress(), port);
        SettingsHelper.saveIp(this, getTcpAddress());
        SettingsHelper.savePort(this, getTcpPortNumber());
      } catch (NumberFormatException e) {
        setStatus("Port Number Is Invalid", Color.RED);
        return null;
      }
    }

    try {
      connection.open();
      setStatus("Connected", Color.GREEN);
    } catch (ConnectionException e) {
      setStatus("Comm Error! Disconnecting", Color.RED);
      DemoSleeper.sleep(1000);
      disconnect();
    }

    ZebraPrinter printer = null;

    if (connection.isConnected()) {
      try {

        printer = ZebraPrinterFactory.getInstance(connection);
        setStatus("Determining Printer Language", Color.YELLOW);
        String pl = SGD.GET("device.languages", connection);
        setStatus("Printer Language " + pl, Color.BLUE);
      } catch (ConnectionException e) {
        setStatus("Unknown Printer Language", Color.RED);
        printer = null;
        DemoSleeper.sleep(1000);
        disconnect();
      } catch (ZebraPrinterLanguageUnknownException e) {
        setStatus("Unknown Printer Language", Color.RED);
        printer = null;
        DemoSleeper.sleep(1000);
        disconnect();
      }
    }

    return printer;
  }

  public void disconnect() {
    try {
      setStatus("Disconnecting", Color.RED);
      if (connection != null) {
        connection.close();
      }
      setStatus("Not Connected", Color.RED);
    } catch (ConnectionException e) {
      setStatus("COMM Error! Disconnected", Color.RED);
    } finally {
      enableTestButton(true);
    }
  }

  private void setStatus(final String statusMessage, final int color) {
    runOnUiThread(new Runnable() {
      public void run() {
        statusField.setBackgroundColor(color);
        statusField.setText(statusMessage);
      }
    });
    DemoSleeper.sleep(1000);
  }


  private void sendTestLabel() {
    try {
      ZebraPrinterLinkOs linkOsPrinter = ZebraPrinterFactory.createLinkOsPrinter(printer);

      PrinterStatus printerStatus = (linkOsPrinter != null) ? linkOsPrinter.getCurrentStatus() : printer.getCurrentStatus();

      if (printerStatus.isReadyToPrint) {
        byte[] configLabel = getConfigLabel();
        connection.write(configLabel);
        setStatus("Sending Data", Color.BLUE);
      } else if (printerStatus.isHeadOpen) {
        setStatus("Printer Head Open", Color.RED);
      } else if (printerStatus.isPaused) {
        setStatus("Printer is Paused", Color.RED);
      } else if (printerStatus.isPaperOut) {
        setStatus("Printer Media Out", Color.RED);
      }
      DemoSleeper.sleep(1500);
      if (connection instanceof BluetoothConnection) {
        String friendlyName = ((BluetoothConnection) connection).getFriendlyName();
        setStatus(friendlyName, Color.MAGENTA);
        DemoSleeper.sleep(500);
      }
    } catch (ConnectionException e) {
      setStatus(e.getMessage(), Color.RED);
    } finally {
      disconnect();
    }
  }

  private void enableTestButton(final boolean enabled) {
    runOnUiThread(new Runnable() {
      public void run() {
        testButton.setEnabled(enabled);
      }
    });
  }

  /*
   * Returns the command for a test label depending on the printer control language
   * The test label is a box with the word "TEST" inside of it
   *
   * _________________________
   * |                       |
   * |                       |
   * |        TEST           |
   * |                       |
   * |                       |
   * |_______________________|
   *
   *
   */
  private byte[] getConfigLabel() {
    byte[] configLabel = null;
    try {
      PrinterLanguage printerLanguage = printer.getPrinterControlLanguage();
      SGD.SET("device.languages", "zpl", connection);

      if (printerLanguage == PrinterLanguage.ZPL) {
        configLabel = "^XA^FO17,16^GB379,371,8^FS^FT65,255^A0N,135,134^FDTEST^FS^XZ".getBytes();
      } else if (printerLanguage == PrinterLanguage.CPCL) {
        String cpclConfigLabel = "! 0 200 200 406 1\r\n" + "ON-FEED IGNORE\r\n" + "BOX 20 20 380 380 8\r\n" + "T 0 6 137 177 TEST\r\n" + "PRINT\r\n";
        configLabel = cpclConfigLabel.getBytes();
      }
    } catch (ConnectionException e) {

    }
    return configLabel;
  }



  private void doConnectionTest() {
    printer = connect();

    if (printer != null) {
      sendTestLabel();
    } else {
      disconnect();
    }
  }

  private void toggleEditField(EditText editText, boolean set) {
    editText.setEnabled(set);
    editText.setFocusable(set);
    editText.setFocusableInTouchMode(set);
  }

  private boolean isBluetoothSelected() {
    return btRadioButton.isChecked();
  }

  private String getMacAddressFieldText() {
    return macAddressEditText.getText().toString();
  }

  private String getTcpAddress() {
    return ipAddressEditText.getText().toString();
  }

  private String getTcpPortNumber() {
    return portNumberEditText.getText().toString();
  }
}
