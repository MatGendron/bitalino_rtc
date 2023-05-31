part of 'bitalino.dart';

/// Object that has the data received from the bluetooth device.
/// RTC: Adding pull of time data from data map
class BITalinoFrame {
  BITalinoFrame._fromPlatformData(Map<dynamic, dynamic> data)
      : identifier = data['identifier'] ?? "",
        sequence = data['sequence'],
        analog = List<int>.from(data['analog']),
        digital = List<int>.from(data['digital']),
        hours = data['hours'],
        minutes = data['minutes'],
        seconds = data['seconds'],
        milliseconds = data['milliseconds'];

  /// MAC address of the device that sent the frame.
  final String identifier;

  /// Sequence number of the frame. Two consecutive frames must have a sequence difference of 1.
  final int sequence;

  /// List with all the analog channel values of the frame.
  final List<int> analog;

  /// List with all the digital channel values of the frame.
  final List<int> digital;

  /// RTC: Time data fields
  final int hours;

  final int minutes;

  final int seconds;

  final int milliseconds;
}
