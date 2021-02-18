/*
Google Code Jam Solution
author: Casey Barton
completion time:
*/
"use strict"
var input = [];//input[2][3] = line 2 word 3
var output = '';


function calculateDamage(P){
    var d = 0;
    var currentMultiplier = 1;
    for (let i = 0; i < P.length; i++) {
        if(P.charAt(i) == 'S'){
            d += currentMultiplier;
        }else{
            currentMultiplier *= 2;
        }
    }
    return d;
}

function findLatestSwappableS(P){
    var i = P.length - 1;
    while(i>0){
        if(P.charAt(i) == 'S' && P.charAt(i-1) == 'C'){
            return i;
        }
        i--;
    }
    return -1;
}

function swapChars(str, charPos0, charPos1){
    var newstr = str.substring(0, charPos0) + str.charAt(charPos1) + str.substring(charPos0 + 1, charPos1) + str.charAt(charPos0) + str.substring(charPos1 + 1, str.length);
    return newstr;
}


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


        for (let t = 1; t < parseInt(input[0][0], 10) + 1; t++) {
            var D = parseInt(input[t][0], 10);
            var P = input[t][1];
            var currentString = P;
            var numHacks = 0;
            let impossible = false;

            while(calculateDamage(currentString) > D){
                let latestSwappableS = findLatestSwappableS(currentString);
                if(latestSwappableS == -1){
                    output = output.concat(`Case #${t}: IMPOSSIBLE\n`);
                    impossible = true;
                    break;
                }
                currentString = swapChars(currentString, latestSwappableS - 1, latestSwappableS);
                numHacks++;
            }

            if(!impossible)
                output = output.concat(`Case #${t}: ${numHacks}\n`);

        }


        //write output
        process.stdout.write(output);
    }
});