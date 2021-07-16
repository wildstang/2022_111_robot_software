# Shuffleboard

[Official Documentation](https://docs.wpilib.org/en/stable/docs/software/wpilib-tools/shuffleboard/index.html)

Shuffleboard is a Java application packaged with the Driver Station which allows GUI-based input and output.
Shuffleboard is the successor to SmartDashboard which is still included and uses the same API.
The most common use case is displaying debug information without spamming the Driver Station log,
but it can also be used for setting robot parameters remotely.

## Raw Value Display

Numbers and booleans can be added to Shuffleboard's SmartDashboard tab using their corresponding put function and passing a label and the value.

```
SmartDashboard.putNumber("Number Label", value);
SmartDashboard.putBoolean("Boolean Label", value);
```

## Dropdown Selector

A dropdown selector can be added to Shuffleboard using the `SendableChooser` class.
The `SendableChooser` is a generic class meaning it can be melded to work with any Object.
Each option is individually added to the chooser by passing a label and an instance of the Object.
Via `putData()` the chooser is added to Shuffleboard with a label.
Finally, the currently selected Object can be returned by calling `chooser.getSelected()`.
We use these fields for setting auto programs and log levels.

```
SendableChooser<Object> chooser;
chooser.addOption("Option 1", object1);
chooser.addOption("Option 2", object2);
SmartDashboard.putData("Chooser", chooser);

Object selected = chooser.getSelected()
```

## Editable Data

Shuffleboard added more features beyond SmartDashboard, this includes editable text fields.

### Tabs

Shuffleboard's API works using tabs, before accessing an entry its tab must be selected.

```
ShuffleboardTab tab = Shuffleboard.getTab("Tab");
```

### Setting Value

Each entry added to a tab requires a label and a default value.
Then using the returned `NetworkTableEntry` a value(s) can be set.

```
NetworkTableEntry entry = tab.add("Label", defaultValue);
entry.setDouble(value);
entry.setBoolean(value);
entry.setString(value);
entry.setRaw(values);
entry.setDoubleArray(values);
entry.setBooleanArray(values);
entry.setStringArray(values);
```

### Getting Value

Once placed a user can edit the value on Shuffleboard, then the following functions can be used to retrieve the value.

```
double    value  = entry.getDouble(defaultValue);
boolean   value  = entry.getBoolean(defaultValue);
String    value  = entry.getString(defaultValue);
byte[]    values = entry.getRaw(defaultValues);
double[]  values = entry.getDoubleArray(defaultValues);
boolean[] values = entry.getBooleanArray(defaultValues);
String[]  values = entry.getStringArray(defaultValues);
```