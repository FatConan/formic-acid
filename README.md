# formic-acid
Formic-acid is a form handling approach (based around JSON passing) extracted from a production project so that it can be improved and harnessed in isolation to aid continuous improvement.

Formic is a set of tools designed around extracting and validating data in JSON format against a collection of constructed rules.  It is designed for use
behind JSON based REST APIs where it can be used to validate incoming user data before persisting it to databases or passing it on to other processes. 

Additionally its original intention was to be used alongside HTML forms (for collecting data from users) and to that end it 
has recently acquired some templating aspects so that forms can be designed in a standardised way (based on Play's Twirl templating engine) so that
Formic can be used (should one wish) to construct forms as well as validate data harnessed from them.

Of course, HTML forms don't submit data in JSON format and can't be populated directly from JSON either (in the case of editing)
so to add the functionality to perform those actions (as well as supporting mechanisms to submit data and add form interactivity) 
can be provided through a new Javascript library called [Malic-acid](https://github.com/FatConan/malic-acid) which is currently
in an alpha state (and very much in need of a tidy up).

Eventually I'll likely also add a Java bridge based around the same approach Malic uses to translate multipart form data into JSON
in a flexible, but convenient way so that it can be used with basic form submissions (rather than the AJAX communication that Malic uses).
This could then provide a basis for using existing REST API endpoints with form submissions through either some form of proxy, or wrapping around existing Java code.
This is still very loosely defined, but I'll solidify that in future releases.

# Build
Run 
```
sbt clean package
```
to build the formic jar or 
```
mvn clean publishLocal
```
to add it to your local maven repo.

# Usage

## JSON Formats
