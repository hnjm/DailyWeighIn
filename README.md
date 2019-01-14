# DailyWeighIn

This Java application takes in the user's current weight (intended as a daily-usage application) and adds it to a .csv file.

Using this comma-separated values file, the application then runs a trio of Python scripts, entitled '*Plot7.py*', '*Plot28.py*', and '*Plot365.py*'. 

These scripts create .png graph files for the previous 7, 28, and 365 days of entries from the user. Once all of the graphs have been successfully produced, the Java program (that has been patiently waiting for the final graph to exist) will then display the updated graphs on its graphical user interface (if the program has been used before, the previous set of graphs will have initially been displayed, before being replaced at this point).

Satisfied with its own work, the application will then call *CommitToGit.py*. 

*CommitToGit.py* does exactly what you might expect it to do; It commits the changes, and pushes them here to GitHub.

However, the fun doesn't stop there!

*CommitToGit.py*'s parting gift to us all is that it will pass on the new weigh-in variable it received from the Java application to a final Python script, aptly named '*UpdateWebsite.py*'.

This final script opens Google Chrome and navigates to the administration panel at x4iiiis.com and updates the weigh-in database with the new value.

You can keep up with my progress at [x4iiiis.com/bodybuilding](https://www.x4iiiis.com/bodybuilding)!
