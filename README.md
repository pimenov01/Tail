# Tail
Selecting the end of a text file of a certain size.
● file specifies the name of the input file. If this parameter is omitted, you should read input data from console input. If there are several parameters, then before output for each file you should output its name in a separate line line.
● The -o ofile flag specifies the name of the output file (in this case, ofile). If
if this parameter is omitted, output the result to console output.
● The flag -c num, where num is an integer, indicates that the file needs to extract the last num characters.
● The-n num flag, where num is an integer, indicates that the file needs to extract the last num rows.
Command line: tail [-c num|-n num] [-o ofile] file0 file1 file2 …
If any of the file names are incorrect or specified at the same time
the-c and-n flags should return an error. If none of these flags is specified, you should print the last 10 lines.
