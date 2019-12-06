# AOSPIssue145770600
Project for showcasing [AOSP Issue 145770600](https://issuetracker.google.com/issues/145770600)

# Problem description
When you have set your device in dark mode and try to connect to a hotspot network with the new 
[peer to peer API in Android 10](https://developer.android.com/guide/topics/connectivity/wifi-bootstrap)
the system dialog which then appears is not styled correctly. It has dark text on a dark background.
The connection timeout dialog does have proper dark mode styling.

# Steps to reproduce
- Have some device you can boot in hotspot mode (I.e. a Chromecast or what have you)
- Turn on dark mode on your device
- Start this app
- Fill in your hotspot's SSID (verbatim, case sensitive) in the form field
- Press the "Connect to hotspot" button
- The dialog that then appears is not styled correctly

![screenshot of faulty system dialog](https://github.com/jpelgrim/AOSPIssue145770600/blob/master/faulty-system-dialog.png.png "Faulty system dialog")
