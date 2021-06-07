# WildStang Robot Framework

This is the core framework for WildStang's FRC robot software development.

## Robot Framework Initialization

In order to create a new code base for a new robot follow these steps:
1. Fork this repo into a 20XX_robot_software repo
2. In `src/main/java/org/wildstang/` duplicate `sample` to `20XX`
3. Rename package accordingly in each class and `ROBOT_MAIN_CLASS` in `build.gradle`
4. Update `edu.wpi.first.GradleRIO` version in `build.gradle` to latest WPILib version
5. Update `frcYear` in `settings.gradle` to competition year
6. Update `projectYear` in `.wpilib/wpilib_preferences.json` to competition year
7. Update `teamNumber` in `.wpilib/wpilib_preferences.json` if necessary

## Setting up software
- VS Code and other required components
  - [Instructions](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/wpilib-setup.html)
  - [Download](https://github.com/wpilibsuite/allwpilib/releases/latest/)
- Driver Station and Utilities
  - [Instructions](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-2/frc-game-tools.html)
  - [Download](https://www.ni.com/en-us/support/downloads/drivers/download.frc-game-tools.html#369633/)
- RoboRIO Imager
  - [Instructions](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/imaging-your-roborio.html)
  - Included with Driver Station
- 2020 Radio Configuration Utility
  - [Instructions](https://docs.wpilib.org/en/stable/docs/zero-to-robot/step-3/radio-programming.html)
  - [Download](https://firstfrc.blob.core.windows.net/frc2020/Radio/FRC_Radio_Configuration_20_0_0.zip)
- Phoenix Tuner (CAN)
  - [Download](https://github.com/CrossTheRoadElec/Phoenix-Releases/releases/latest/)

### Building and deploying to the robot

To build/deploy/debug the robot code either right click on `build.gradle` and choose the desired option or open the command pallete and search and select `WPILib: [FUNCTION] robot code`. Robot code may also be deployed by pressing `Shift + F5`.

To open the command palette use:
- F1
- Ctrl + Shift + P
- Cmd + Shift + P
- Select the WPILib Command Palette 'W' button in the top right
