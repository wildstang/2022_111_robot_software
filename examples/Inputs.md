# Inputs

## Buttons
```
DigitalInput input;
boolean pressed;

public void init() {
    input = (DigitalInput) Core.getInputManager().getInput(WSInputs.BUTTON_ID);
    input.addInputListener(this);
}

public void inputUpdate(Input signal) {
    if (signal == input) {
        if (signal.getValue()) {
            pressed = true;
        }
        else {
            pressed = false;
        }
    }      
}
``` 

## Joysticks
```
AnalogInput input;
double value;

public void init() {
    input = (AnalogInput) Core.getInputManager().getInput(WSInputs.BUTTON_ID);
    input.addInputListener(this);
}

public void inputUpdate(Input signal) {
    if (signal == input) {
        value = input.getValue();
    }      
}
``` 

## Triggers
```
AnalogInput input;
boolean pressed;

public void init() {
    input = (AnalogInput) Core.getInputManager().getInput(WSInputs.BUTTON_ID);
    input.addInputListener(this);
}

public void inputUpdate(Input signal) {
    if (signal == input) {
        if (Math.abs(signal.getValue()) > 0.1) {
            pressed = true;
        }
        else {
            pressed = false;
        }
    }      
}
``` 