
Build and Install
=================

This document describes installation on the following operating
systems:
- Unix/Linux 
- Windows

Table of Contents
=================

 - [Prerequisites]
 - [Installation Steps]
 - [Configuration]
 - [Notes]


Prerequisites
=============

To install ShareYourTrip, you must have the following software:

- AndroidStudio (https://developer.android.com/studio/install)
- Git (https://git-scm.com/downloads)


Installation Steps
==================

To install ShareYourTrip, you may follow the following steps for
the given operating systems.

### Unix/Linux 
In the terminal, do:

1. Go to or create a local project directory on your device:
	cd home/path-to-your-project-directory

2. Create a new subdirectory called .git:
	.git

3. Clone the ShareYourTrip repository 
	git clone https://github.com/WSU-4110/ShareYourTrip.git

### Windows
In the terminal, do:

1. Go to or create a local project directory on your device:
	cd C:/path-to-your-project-directory

2. Create a new subdirectory called .git:
	.git

3. Clone the ShareYourTrip repository 
	git clone https://github.com/WSU-4110/ShareYourTrip.git



Configuration
=============

No configuration needed.



Notes
=====

Linux: to run the emulator on AndroidStudio, it may be necessary 
to grant /dev/kvm device permissions. To do so, it is recommented 
to run the following commands in the terminal:

1. To check ownership:
	ls -al /dev/kvm

2. To check which users are in the 'kvm' group:
	grep kvm /etc/group

3. If current user is not added, then do the following:
	sudo adduser $USER kvm

(Ref: https://stackoverflow.com/questions/37300811/android-studio-dev-kvm-device-permission-denied/45749003)

