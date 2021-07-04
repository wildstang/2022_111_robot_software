# Connecting to the Robot

## Addressing

If connected through a radio, the RoboRIO will generally be assigned the IP address `10.TE.AM.2` from DHCP.
`TEAM` is the team number the radio was configured with, `AM` is always populated.
If directly connected in a "link-local" configuration it will self-assign an IP address in the `169.254.XX.YY` range.
If connected with USB and the USB driver is installed from the NI FRC Game Tools, the RIO will be assigned the IP address `172.22.11.2` and your PC should be assigned a corresponding address by the driver.
In any case, if you have Apple's Bonjour or an mDNS provider installed (automatically installed with NI FRC Game Tools) the robot can be accessed at `roboRIO-TEAM-FRC.local`.
This mDNS address should also work when a custom address is set by either static configuration or from another DHCP server (router).
[See this article](https://docs.wpilib.org/en/stable/docs/networking/networking-introduction/networking-basics.html) for all the gory FRC networking details and [this article](https://docs.wpilib.org/en/stable/docs/networking/networking-introduction/ip-configurations.html) for official IP documentation.

## Connecting

### VS Code

VS Code, more specifically the gradlew script, should be able to connect to a robot using any of the IP addresses.
If mDNS is not installed, additional addresses may be added by adding `addAddress("ADDRESS")` to the `roboRIO("roborio")` section of `build.gradle`.
It should be noted the RioLog does not appear to work with custom IP addresses unless the Driver Station is also running.

### RoboRIO Imager

The imager connects to the RIO using `roboRIO-TEAM-FRC.local`.

## Radio Configuration Utility

To configure a radio, connect the radio via ethernet to the computer or same network.
Power-cycling the radio may be necessary.
The radio does not need to be networked with the RIO.

### Driver Station

Driver Station connects to `roboRIO-TEAM-FRC.local` once a team number is entered in the gear menu.
If a custom IP address is used, type that into the team number box.
The Driver Station will not drive over USB.

### QDriverStation

The open source QDriverStation defaults to using `10.TE.AM.2` or `roboRIO-TEAM-FRC.local`, once the team number is set in the wrench menu.
Custom IP addresses can be set in the gear menu.

### Phoenix Tuner

Phoenix Tuner works best with a USB connection. To ensure a connection click "Run Temporary Diagnostic Server".
Phoenix Tuner also works over a network, type in the team number or RIO IP address before connecting.
Restarting the tuner can often solve devices not showing up.