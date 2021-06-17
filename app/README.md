# NFiller
Fill android notification log with garbage.

Android keeps a log of notifications in plaintext. This can be seen on most AOSP variants 
(though, not on Samsung phones apparently) by adding a settings shortcut and opening it:

```
Homescreen > Add Widget > Settings > Notifications log
```

This app fills the notification log by creating a number of random fake messages with the goal of 
removing legitimate notifications from the log.

Inspired by the discussion in [this](https://community.signalusers.org/t/warn-users-that-notifications-get-logged/7429/53653) thread on the SignalUsers forum.

## Usage

Enter the number of notifications to show, and click the "Fill Notifications" button. Afterward, to clear the notifications you can click "Clear All"
