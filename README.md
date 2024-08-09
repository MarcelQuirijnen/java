# ProcessBigTable code project

##What was requested
Read a locations table with around 50+ million records and dump it to a CSV file.
##Code
ProcessBigTable.java
##Example Output
data.csv (<10 records)
##Automation Folder
Contains configuration files for :
- Setup a linux box with MySQL and the MySQL java lib using Vagrant
- Ensures MySQl is up and running, even after reboot (Ansible)
- Sets up an application database (Ansible)
- Sets up an application user and password (Ansible)
- Transfers the table creation script and executes it (Ansible)
- Adds seed data to the location table (Ansible)

Contains a very basic Jenkins file to ensure basic testing and CI. 
Not tied into Git (bitbucket, subversion,..) yet.

##tests folder
Contains the unit test example for the main code.
Uses JUnit v4. Test code is not production ready.

##Thanks
Thanks for the challenge, XModeSocial. I do not consider myself a Java developer, but I enjoyed comming up with a solution.


