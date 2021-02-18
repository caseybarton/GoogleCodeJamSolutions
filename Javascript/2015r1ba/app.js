/*
Google Code Jam Solution
author: Casey Barton
completion time: 4:33
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
  let y = 0;
  let n = input[x][0];
  let counter = 0;

  //handle single digit numbers
  if(n < 20){
    y = n;
    output = output.concat('Case #', x, ': ', y, '\n');
    continue;
  }

  //handle numbers like 4000
  let lowerHalfDigitsLength = Math.ceil(getSignificantDigits(n) / 2);
  let lowerHalfDigits = sliceLowestDigits(n, lowerHalfDigitsLength);
  if(lowerHalfDigits == 0){
    n--;
    y++;
  }

  let upperHalfDigitsLength = Math.floor(getSignificantDigits(n) / 2);
  lowerHalfDigitsLength = Math.ceil(getSignificantDigits(n) / 2);
  let upperHalfDigits = sliceLowestDigits(reverseNumber(n), upperHalfDigitsLength); //(reversed)
  lowerHalfDigits = sliceLowestDigits(n, lowerHalfDigitsLength);


  counter = Math.pow(10, getSignificantDigits(n) - 1);
  if(counter >= 10)
    y += 10;
  if(counter >= 100)
    y += 19;
  if(counter >= 1000)
    y += 109;
  if(counter >= 10000)
    y += 199;
  if(counter >= 100000)
    y += 1099;
  if(counter >= 1000000)
    y += 1999;
  if(counter >= 10000000)
    y += 10999;
  if(counter >= 100000000)
    y += 19999;
  if(counter >= 1000000000)
    y += 109999;
  if(counter >= 10000000000)
    y += 199999;
  if(counter >= 100000000000)
    y += 1099999;
  if(counter >= 1000000000000)
    y += 1999999;
  if(counter >= 10000000000000)
    y += 10999999;

  // while(getSignificantDigits(n) > getSignificantDigits(counter)){
  //   counter += 9; //1009
  //   y += 9;
  //   if(!isPalindrome(counter)){
  //     counter = reverseNumber(counter); //9001
  //     y += 1;
  //   }
  //   let distanceToNextSignificantDigit = Math.pow(10, getSignificantDigits(counter)) - counter;
  //   counter += distanceToNextSignificantDigit; //10000
  //   y += distanceToNextSignificantDigit;
  // }


  counter += upperHalfDigits;
  y += upperHalfDigits;
  if(!isPalindrome(counter)){
    counter = reverseNumber(counter);
    y++;
  }
  counter += lowerHalfDigits - 1;
  y += lowerHalfDigits - 1;


  output = output.concat('Case #', x, ': ', y, '\n');
}





//write output file
fs.writeFileSync('out.txt', output);


function reverseNumber(n){
  let digits = Array(); //0 is most significant digit = 10^14
  let divisor = Math.pow(10, 14);
  //create digit array
  for (let i = 14; i >= 0; i--) {
    digits.push(Math.trunc(n / divisor));
    n = n - Math.trunc(n / divisor) * divisor;
    divisor = divisor / 10;
  }

  //remove leading zeros
  let firstDigit = 0;
  while(digits[firstDigit] == 0){
    firstDigit++;
  }
  digits = digits.slice(firstDigit, digits.length);

  //flip it
  let ret = 0;
  for (let i = digits.length - 1; i >= 0; i--) {
    ret += digits[i] * Math.pow(10, i);
  }
  return ret;
}

function isPalindrome(n){
  let upperHalfDigitsLength = Math.floor(getSignificantDigits(n) / 2);
  let lowerHalfDigitsLength = Math.floor(getSignificantDigits(n) / 2);
  let upperHalfDigits = sliceLowestDigits(reverseNumber(n), upperHalfDigitsLength); //(reversed)
  let lowerHalfDigits = sliceLowestDigits(n, lowerHalfDigitsLength);
  return upperHalfDigits == lowerHalfDigits;
}

function getSignificantDigits(n){
  return Math.trunc(Math.log10(n)) + 1;
}

function getDigit(n, digit){ //least significant digit is 0
  let digits = Array(); //0 is most significant digit = 10^14
  let divisor = Math.pow(10, 14);
  //create digit array
  for (let i = 14; i >= 0; i--) {
    digits.push(Math.trunc(n / divisor));
    n = n - Math.trunc(n / divisor) * divisor;
    divisor = divisor / 10;
  }
  return digits[14-digit];
}

function sliceLowestDigits(n, numDigits){
  return n % Math.pow(10, numDigits);
}

///////////////////////////////////////////////////////////////////////////////
//some useful functions
///////////////////////////////////////////////////////////////////////////////

function zeroInitializedArray(n) {
  var arr = Array.apply(null, Array(n));
  return arr.map(function (x, i) { return 0 });
}

function isArrayAllZero(arr) {
  let ret = true;
  arr.forEach(function (e) {
    if (e != 0) {
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
