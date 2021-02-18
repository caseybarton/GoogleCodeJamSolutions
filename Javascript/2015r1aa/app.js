/*
Google Code Jam Solution
author: Casey Barton
completion time: ~2:00:00
 */
const fs = require('fs');

var input = [];//input[0][3] = line 0 word 3
var output = '';

//read input file
var inputString = fs.readFileSync('in.txt', 'utf8');
var lines = inputString.split('\n');
for (let i = 0; i < lines.length; i++) {
  input[i] = lines[i].split(' ');
  for (let j = 0; j < input[i].length; j++) {
    input[i][j] = parseInt(input[i][j]);
  }
}

//CODE JAM!
for (let x = 1; x <= input[0][0]; x++) {
  let totaldiff = 0;//cumulative difference
  let maxdiff = 0;//greatest difference
  for (let i = 0; i < input[x * 2 - 1][0] - 1; i++) {
    let diff = input[x * 2][i] - input[x * 2][i + 1];
    diff = diff < 0 ? 0 : diff;
    maxdiff = diff > maxdiff ? diff : maxdiff;
    totaldiff += diff;
  }
  let y = totaldiff;
  let z = 0;
  for (let i = 0; i < input[x * 2 - 1][0] - 1; i++) {
    if(input[x * 2][i] < maxdiff){
      z += input[x * 2][i];
    }else{
      z += maxdiff;
    }
    console.log(z);
  }
  //let z = maxdiff * (input[x * 2 - 1][0] - 1);
  output = output.concat('Case #', x, ': ', y, ' ', z, '\n');
}


//write output file
fs.writeFileSync('out.txt', output);