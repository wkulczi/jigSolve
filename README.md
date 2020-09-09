#  ![App logo](https://i.ibb.co/zhk5XjC/wa.jpg) - puzzle solver application  
--------
## Introduction
Having a hard time with solving the jigsaw puzzles? Want a hand? Well, we're here.
## Purpose
This application was created as a project for the subject named Podstawy Teleinformatyki (Basics of Teleinformatics) at [PUT](https://www.put.poznan.pl/).
## Under the hood
Python server runs SIFT algorithm to find the features matching the two images, that way you can tell where the piece should be placed. The information is being sent to the mobile application. That's it, tada~! 🎉
## How to run
Mobile app: 
In the `client` folder you'll find your everyday android studio project. With Android 10.0(Q) & api level 29 you should have next to no problems with running it with the IDE or gradle.
Find the `192.168...` line of code and add ip address on which the server application is avaiable.

Now for the tricky part, you have 2 options of configuring the backend application.

###### Option I (easy, but might not work)
- USE ___PYTHON 3.7.6__ or older.
- Install libraries accordingly to the `requirements.txt` file (`pip install -r requirements.txt`)
- run with `python run.py`

###### Option II (solid, but harder)
- use whatever Python version you desire (but not <3.0, don't be that guy)
- use [this tutorial](https://www.pyimagesearch.com/2018/08/15/how-to-install-opencv-4-on-ubuntu/) to install opencv4 with ENABLE_NONFREE flag
- install rest of the required packages by yourself (__opencv-contrib-python is not needed anymore!__)
- run with `python run.py`

##### Requirements
- Python (>3.0)
- Java (compiled with Oracle Java 1.8)
- Android SDK (10.0 ver.)
- Patience
- Coffee