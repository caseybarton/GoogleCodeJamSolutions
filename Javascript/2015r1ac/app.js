/*
Google Code Jam Solution
author: Casey Barton
completion time: 8:30 (large solution is incorrect)
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
let nextTestCase = 1;
for (let x = 1; x <= input[0][0]; x++) {
  let N = input[nextTestCase][0]; //number of trees

  let trees = input.slice(nextTestCase + 1, nextTestCase + input[nextTestCase][0] + 1);
  nextTestCase = nextTestCase + input[nextTestCase][0] + 1;

  let y = new Array(N);

  //for each tree
  for (let n = 0; n < N; n++) {//n - tree
    //first, handle case of only one tree (two trees will work, but just to be safe...)
    if(N == 1 || N == 2){
      y[n] = 0;
      continue;
    }
    //create a sorted array of angles for each other tree
    let treeAngles = Array();
    for (let na = 0; na < N; na++) {//na - tree angle
      if(n != na){
        let thisX = trees[n][0];
        let thisY = trees[n][1];
        let thatX = trees[na][0];
        let thatY = trees[na][1];
        let deltaX = thatX - thisX;
        let deltaY = thatY - thisY;
        treeAngles.push(Math.atan2(deltaY, deltaX) * 180 / Math.PI);
      }
    }

    function numericalComparison(a,b) {
      return a - b;
    }
    treeAngles.sort(numericalComparison);

    //for the sake of simplicity, duplicate the angles adding 360 degrees to duplicates
    for (let na = 0; na < N-1; na++) {
      treeAngles.push(treeAngles[na] + 360);
    }

    //given the list of angles, create a list of the number of trees within the next 180 degrees inclusive
    //if there's any interval >=180, y[n] is zero
    let treesInIntervals = Array(N-1);
    for (let na = 0; na < N-1; na++) {
      let treeStart = na;
      let treeEnd = na+1;
      while(treeAngles[treeEnd] - treeAngles[treeStart] < 180){
        treeEnd++;
      }
      if(treeEnd - treeStart == 1 && treeAngles[treeEnd] - treeAngles[treeStart] >= 180){
        treesInIntervals[na] = 0;
      }else{
        treesInIntervals[na] = treeEnd - treeStart - 1;
      }
    }

    //get the smallest number from that list, that's y[n]
    let min = 3001;
    for (let na = 0; na < N-1; na++) {
      if(treesInIntervals[na] < min){
        min = treesInIntervals[na];
      }
    }
    y[n] = min;
  }

  output = output.concat('Case #', x, ':\n');
  for (let i = 0; i < y.length; i++) {
    output = output.concat(y[i], '\n');
  }
}


//write output file
fs.writeFileSync('out.txt', output);