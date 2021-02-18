const fs = require('fs');

words = ['Hello', 'world', 'and', 'stuff'];

fs.open('out', 'w', (err, fd) => {
  for (var i = 0; i < words.length; i++) {
    fs.write(fd, words[i] + ' ',  (err, written, string) => {}); 
  }
});


