mbox_parser
===========

java parser for *.mbox files. Returns list of senders with count of emails.
For run it type in terminal:

cd [repository_folder_path] 

cat [mbox_file_path] | java -classpath ./bin com.grishin.mboxparser.main.Main

if you want use alias file type this:

cd [repository_folder_path] 

cat [mbox_file_path] | java -classpath ./bin com.grishin.mboxparser.main.Main -a [aliases_file_path]
