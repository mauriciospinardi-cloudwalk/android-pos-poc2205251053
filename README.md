# POC 2205251053: `Available networks` activity

<img src="SHIELD.svg"/><br/>

(P)roof (O)f (C)oncept on how to create an `Available networks` activity
shortcut for Sunmi P2-B/EU devices.  

## Development notes

(ROM v3.0.65) Calls for `Settings#ACTION_NETWORK_OPERATOR_SETTINGS` appear to
trigger an infinite search for available networks. Manual triggering of the same
activity thought `Settings` application does not present same issue.  
