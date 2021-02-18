/*
Google Code Jam Solution
author: Casey Barton
completion time: 31:27 (It seems google's going easy on the Indians)
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
  let R = xInput[0];
  let C = xInput[1];
  let W = xInput[2];
  let y = 0;


  //remove all places ship can move, last one will be a hit
  y += Math.floor(C/W) * R;

  //destroy ship
  if(C%W != 0)
    y += 1; //second missile lands in water

  y += W - 1; //ship's trapped

  return y;
}


let xInputStart = 1;
for (let x = 1; x <= input[0][0]; x++) {
  //x is single line
  let xInput = (input.slice(x, x+1))[0];

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
