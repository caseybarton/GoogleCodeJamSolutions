/*
Google Code Jam Solution
author: Casey Barton
completion time:
 */
'use strict'

var input = [];//input[2][3] = line 2 word 3
var output = '';

function troubleSort(L){
    var done = false;
    while(!done){
        done = true;
        for (let i = 0; i < L.length - 2; i++) {
            if(L[i] > L[i+2]){
                done = false;
                let temp = L[i];
                L[i] = L[i+2];
                L[i+2] = temp;
            }
        }
    }
    return L;
}

function findError(L){
    for(let i = 0; i < L.length - 1; i++) {
        if(L[i] > L[i+1]){
            return i;
        }
    }
    return -1; //no error
}

process.stdin.setEncoding('utf8');
process.stdin.on('readable', () => {
    const chunk = process.stdin.read();
    if (chunk !== null) {
        var inputString = chunk;
        var lines = inputString.split('\n');
        for (let i = 0; i < lines.length; i++) {
            input[i] = lines[i].split(/[ ]+/).filter(function(word) {return word.length != 0});
        }


        //CODE JAM!
        for (let t = 1; t < parseInt(input[0][0], 10) + 1; t++) {
            var unsortedArray = [];
            for (let i = 0; i < input[2 * t].length; i++) {
                unsortedArray[i] = parseInt(input[2 * t][i], 10);
            }
            var sortedArray = troubleSort(unsortedArray);
            var error = findError(sortedArray);
            if(error == -1){
                output = output.concat(`Case #${t}: OK\n`);
            }else{
                output = output.concat(`Case #${t}: ${error}\n`);
            }
        }



        //write output
        process.stdout.write(output);
    }
});