const express = require('express');
const cors = require('cors');
const { exec } = require('child_process');
const path = require('path');

const app = express();
app.use(cors());

app.use(express.static(path.join(__dirname, 'public')));

app.get('/graph', function (req, res) {
    // Extract query parameters
    const { n, p } = req.query;
  
    // Run the Java command
    exec(`java -cp "../out/production/RandomGraphBuilder;../backend/libs/json-20230618.jar" com.Lakshman.RandomGraphBuilder.Main ${n} ${p}`, (error, stdout, stderr) => {
      if (error) {
        console.error(`exec error: ${error}`);
        return;
      }
      
      // Parse the stdout as a string
      const data = stdout;
  
      // Send the data back to the client
      res.json({ graphData: data });
    });
  });

app.get('/test', function (req, res) {
    res.send('Test route is working');
  });
  

app.listen(3000, () => {
    console.log('Server is running on port 3000');
});
