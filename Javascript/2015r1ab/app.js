/*
Google Code Jam Solution
author: Casey Barton
completion time: 6:20:00
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
// for (let x = 1; x <= input[0][0]; x++) {
//   let B = input[x*2-1][0];//num barbers
//   let N = input[x*2-1][1];//position in line
//   let Mk = input[x*2];//barber service times
//   let y = 0;//barber to service me
//
//   let nextCustomer = 1;
//   let kRemainingTimes = zeroInitializedArray(B);
//
//   let firstLoop = true;
//   while(y == 0){
//     //detect pattern and simulate
//     if(isArrayAllZero(kRemainingTimes) && !firstLoop){
//       let newNextCustomer = (nextCustomer-1)*(Math.trunc(N/(nextCustomer-1))-1) + 1;
//       nextCustomer = newNextCustomer > nextCustomer ? newNextCustomer : nextCustomer;
//     }
//     firstLoop = false;
//
//
//     //fill empty barbers
//     kRemainingTimes.forEach(function(e,i){
//       if(e == 0) {
//         if (nextCustomer == N) {
//           y = i + 1; //barbers are 1 initialized, their times aren't
//         }
//         nextCustomer++;
//         kRemainingTimes[i] = Mk[i];
//       }
//     })
//
//     //wait
//     let waitTime = Math.min(...kRemainingTimes);
//     kRemainingTimes.forEach(function(e,i){
//       kRemainingTimes[i] = e - waitTime;
//     });
//   }
//
//   output = output.concat('Case #', x, ': ', y, '\n');
// }

//CODE JAM BUT FASTER!
for (let x = 1; x <= input[0][0]; x++) {
  let B = input[x*2-1][0];//num barbers
  let N = input[x*2-1][1];//position in line
  let Mk = input[x*2];//barber service times
  let y = 0;//barber to service me

  let t = 0;
  let customersServiced = 0;

  do{
    t += 1000000;
    customersServiced = getCustomersServiced(Mk, t);
  }while(customersServiced < N);//?

  let tmax = t; //greater than or equal to N
  let tmin = t - 1000000;
  let tmid;

  //find the time elapsed just before N gets serviced
  while(true){
    if(tmax - tmin == 1){
      break;
    }
    tmid = Math.trunc((tmax + tmin)/2);
    if(getCustomersServiced(Mk, tmid) >= N){
      tmax = tmid;
    }else{
      tmin = tmid;
    }
  }

  //tmin is just before N gets serviced
  //tmax is the moment N gets serviced
  customersServiced = getCustomersServiced(Mk, tmin);
  Mk.forEach(function(e, i){
    if(tmax % e == 0){
      customersServiced++;
      if(customersServiced == N){//?
        y = i + 1;
      }
    }
  })

  if(y == 0){//if algorithm broke (t = 0)
    y = N;
  }
  output = output.concat('Case #', x, ': ', y, '\n');
}

//write output file
fs.writeFileSync('out.txt', output);


//t is time elapsed
function getCustomersServiced(Mk, t){
  let ret = 0;
  Mk.forEach(function(e, i){
    ret += Math.trunc(t/e+1);
  })
  return ret;
}

function zeroInitializedArray(n) {
  var arr = Array.apply(null, Array(n));
  return arr.map(function (x, i) { return 0 });
}

function isArrayAllZero(arr){
  let ret = true;
  arr.forEach(function(e){
    if(e != 0){
      ret = false;
    }
  })
  return ret;
}