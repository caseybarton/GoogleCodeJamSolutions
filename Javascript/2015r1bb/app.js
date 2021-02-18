/*
Google Code Jam Solution
author: Casey Barton
completion time: 2:30 (incorrect; greedy algorithm doesn't work)
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
  let y;
  let R = input[x][0];
  let C = input[x][1];
  let N = input[x][2];

  let unhappinessPoints = {value: 0}; //aka walls shared
  let remainingTenants = {value: N}; //that need rooms

  //first fill out a checkerboard pattern
  // XOXOX
  // OXOXO
  // XOXOX
  // OXOXO
  // XOXOX
  let a = 0;
  a += Math.floor(R/2) * Math.floor(C/3) * 2;
  if(R % 2 == 1)
    a += Math.floor(C / 2);
  if(C % 2 == 1)
    a += Math.floor(R / 2);
  if(R % 2 == 1 && C % 2 == 1)
    a++;
  fillRooms(a, 0, unhappinessPoints, remainingTenants);


  //then start filling corners
  let b = 0;
  if(C % 2 == 0){
    b++; //top-right
  }
  if(R % 2 == 0){
    b++; //bottom-left
  }
  if((R + C) % 2 == 1){
    b++; //bottom-right
  }
  fillRooms(b, 2, unhappinessPoints, remainingTenants);

  // XOXOX
  // OXOXO
  // XOXOX
  // OXOXO
  // XOXOX
  //then start filling wall gaps
  let c = 0;
  //top
  c += Math.ceil(C/2) - 1;
  //left
  c += Math.ceil(R/2) - 1;
  //bottom and right - no shortcuts it seems
  if(R % 2 == 0){
    if(C % 2 == 0){
      c += C/2 - 1;
      c += R/2 - 1;
    }else{
      c += Math.ceil(C/2) - 2;
      c += R/2 - 1;
    }
  }else{ //rows are odd
    if(C % 2 == 0){
      c += Math.ceil(R/2) - 2;
      c += C/2 - 1;
    }else{
      c += Math.floor(C/2);
      c += Math.floor(R/2);
    }
  }
  fillRooms(c, 3, unhappinessPoints, remainingTenants);

  //then start filling interior gaps
  fillRooms(10000, 4, unhappinessPoints, remainingTenants);

  //if one dimension is 1, throw out y value and start over
  if(C == 1 || R == 1){
    unhappinessPoints = {value: 0}; //aka walls shared
    remainingTenants = {value: N}; //that need rooms
    let length = (C == 1)? R : C;
    fillRooms(Math.floor(length / 2), 0, unhappinessPoints, remainingTenants);
    fillRooms(1 - length % 2, 1, unhappinessPoints, remainingTenants);
    fillRooms(10000, 2, unhappinessPoints, remainingTenants);
  }

  y = unhappinessPoints.value;
  output = output.concat('Case #', x, ': ', y, '\n');
}

//write output file
fs.writeFileSync('out.txt', output);

function fillRooms(roomsToFill, unhappinessPointsForEach, unhappinessPoints, remainingTenants){
  if(remainingTenants.value == 0){
    return;
  }
  if(roomsToFill <= remainingTenants.value){
    remainingTenants.value -= roomsToFill;
    unhappinessPoints.value += roomsToFill * unhappinessPointsForEach;
  }else{
    unhappinessPoints.value += remainingTenants.value * unhappinessPointsForEach;
    remainingTenants.value = 0;
  }
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
