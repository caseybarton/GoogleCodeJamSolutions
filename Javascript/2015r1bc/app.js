/*
Google Code Jam Solution
author: Casey Barton
completion time:
 */
const fs = require('fs');

let input = [];//input[0][3] = line 0 word 3
let output = '';

//read input file
let inputString = fs.readFileSync('in.txt', 'utf8');
let lines = inputString.split('\n');
for (let i = 0; i < lines.length; i++) {
  input[i] = lines[i].split(' ');
  for (let j = 0; j < input[i].length; j++) {
    input[i][j] = parseInt(input[i][j]);
  }
}

//CODE JAM!
function solve(xInput){
  let y;
  



  return y;
}

let xInputStart = 1;
for (let x = 1; x <= input[0][0]; x++) {
  //x is single line
  // let xInput = input.slice(x, x+1);

  //x is multiline
  // let numLines = (input.length - 1)/input[0][0];
  // let xInput = input.slice(x * numLines, x * (numLines + 1));

  //x is variable line
  let numLines = input[xInputStart][0] + 1; //numLines is 1 greater than stated problem length!
  let xInput = input.slice(xInputStart, xInputStart + numLines);
  xInputStart += numLines;


  output = output.concat('Case #', x, ':');
  let y = solve(xInput);


  //y is single line
  if(!!y.length){
    for (let i = 0; i < y.length; i++) {
      output = output.concat(' '+y[i]);
    }
  }else{
    output = output.concat(' '+y);
  }
  output = output.concat('\n');

  //y is multiline
  // output = output.concat('\n');
  // for (let i = 0; i < y.length; i++) {
  //   if(!!y[0].length){
  //     for (let j = 0; j < y[i].length - 1; j++) {
  //       output = output.concat(y[i][j] + ' ');
  //     }
  //     output = output.concat(y[i][y[i].length - 1]);
  //   }else{
  //     output = output.concat(y[i]);
  //   }
  //   output = output.concat('\n');
  // }

}



//write output file
fs.writeFileSync('out.txt', output);


///////////////////////////////////////////////////////////////////////////////
//some useful functions
///////////////////////////////////////////////////////////////////////////////

function zeroInitializedArray(n) {
  let arr = Array.apply(null, Array(n));
  return arr.map(function (x, i) { return 0 });
}

function isArrayAllValue(arr, val) {
  let ret = true;
  arr.forEach(function (e) {
    if (e != val) {
      ret = false;
    }
  })
  return ret;
}

function sortArrayNumerically(arr){
  function numericalComparison(a,b) {
    return a - b;
  }
  arr.sort(numericalComparison);
}
