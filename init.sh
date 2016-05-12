
echo "Please Enter the command as follows:"
echo "./imageSeg <number of clusters> <input file path> <output file path>"
read arg0 arg1 arg2 arg3 

arg2=$PWD"/"$arg2
arg3=$PWD"/"$arg3

echo "starting java program!"

cd ./bin/ && java part3.Launcher "$arg1" "$arg2" "$arg3"

