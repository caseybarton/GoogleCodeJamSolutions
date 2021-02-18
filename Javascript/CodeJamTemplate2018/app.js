/*
Google Code Jam Solution
author: Casey Barton
completion time: 00:00
*/

var input = [];//input[2][3] = line 2 word 3
var output = '';

process.stdin.setEncoding('utf8');
process.stdin.on('readable', () => {
    const chunk = process.stdin.read();
    if (chunk !== null) {
        var inputString = chunk;
        var lines = inputString.split('\n');
        for (let i = 0; i < lines.length; i++) {
            input[i] = lines[i].split(/[ ]+/).filter(function(word) {return word.length != 0});;
        }


        //CODE JAM!






        //write output
        process.stdout.write(output);
    }
});




