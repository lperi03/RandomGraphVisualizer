# RandomGraphVisualizer
A project that combines the theory of Random Graphs and Minimum Spanning Trees with a bit of fullstack play. The aim is to develop a UI where users \
can input the number of vertices and the probability of adding each possible edge, at which point the backend builds the corresponding random graph. \
If the MST is possible and is built, the user will be given the option to view the MST building itself out using d3.js.
# Backend
The backend handles all the necessary tools for building the random graph, and corresponding MST (if possible) given the input vertices and edge addition probability.
# Frontend
Handles communications between server/client side, and displays information on the localhost server. 
# Use
Users can run the server using node server.js from the frontend directory (make sure to compile the Java project first!) and then try different types of vertices and probabilities to explore the realm  of random graphs.
