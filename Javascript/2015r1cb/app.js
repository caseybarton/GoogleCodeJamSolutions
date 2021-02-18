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
}


//CODE JAM!
function solve(xInput){
  //xInput is either single or multidimensional array of strings
  //y can be either a number or an array
  let y;
  let K = parseInt(xInput[0][0]);
  let L = parseInt(xInput[0][1]);
  let S = parseInt(xInput[0][2]);
  let keyboard = xInput[1][0];
  let targetWord = xInput[2][0];
  let averageBananas;

  //is word possible?
  for (let i = 0; i < L; i++) {
    if(!keyboard.includes(targetWord.substr(i, 1))){
      return 0.0;
    }
  }
  //get max possible bananas
  let maxBananas = Math.floor(S/L);

  //get average bananas
  //get unique keys and frequency
  let uniqueKeys = [];
  for (let i = 0; i < K; i++) {
    for (let j = 0; j <= uniqueKeys.length; j++) {
      if(j == uniqueKeys.length){//key was not found
        uniqueKeys.push({key: keyboard.substr(i, 1), frequency: 1});
      }
      if(keyboard.substr(i, 1) == uniqueKeys[j].key){
        uniqueKeys[j].frequency++;
        break;
      }
    }
  }


  //is slow way possible?
  let possibleStrings = Math.pow(K, S);
  if(possibleStrings < 100000000){
    let wordsFound = 0;
    let keyboardArr = keyboard.split("");
    let targetWordArr = targetWord.split("");
    let currentStringArr = []; //array of indices of keyboardArr (otherwise duplicate keys would be a problem)
    for (let i = 0; i < S; i++) {
      currentStringArr.push(0);
    }

    for (let i = 0; i < possibleStrings; i++) {
      //look for targetWords
      for (let j = 0; j < S-L+1; j++) {
        for (let k = 0; k < L; k++) {
          if(keyboardArr[currentStringArr[j+k]] != targetWordArr[k]){
            break;
          }else if(k == L-1){
            wordsFound++;
          }
        }
      }

      //next permutation
      while(true){
        let lowestDigitToChange = S-1;
        if(currentStringArr[lowestDigitToChange] == K-1){
          currentStringArr[lowestDigitToChange] = 0;
          lowestDigitToChange--;
        }else{
          currentStringArr[lowestDigitToChange]++;
          break;
        }
      }
    }
    averageBananas = wordsFound / possibleStrings;

  }else{//flub it
    let probabilityForStringLengthL = 1.0;
    for (let i = 0; i < L; i++) {
      for (let j = 0; j < uniqueKeys.length; j++) {
        if(uniqueKeys[j].key == targetWord.substr(i, 1)){
          probabilityForStringLengthL = probabilityForStringLengthL * (uniqueKeys[j].frequency/K);
          break;
        }
      }
    }
    averageBananas = probabilityForStringLengthL * (S-L+1);
  }



  y = maxBananas - averageBananas;
  return y;
}


let xInputStart = 1;
for (let x = 1; x <= input[0][0]; x++) {

  //x is multiline
  let numLines = (input.length - 1)/input[0][0];
  let xInput = input.slice((x-1) * numLines + 1, x * numLines + 1);


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
