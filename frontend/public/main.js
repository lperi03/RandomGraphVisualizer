function fetchGraphData(n, p) {
    fetch(`http://localhost:3000/graph?n=${n}&p=${p}`)
        .then(response => response.json())
        .then(data => {
            if (!data.mstEdges) {
                console.error('Invalid data:', data);
                return;
            }

            // Extract vertices from the edges
            let vertices = [];
            let edges = data.mstEdges.map(edge => {
                if (!vertices.includes(edge.source)) vertices.push(edge.source);
                if (!vertices.includes(edge.target)) vertices.push(edge.target);
                return {source: vertices.indexOf(edge.source), target: vertices.indexOf(edge.target)};
            });

            vertices = vertices.map(vertex => {return {id: vertex}});
            
            // Remove the old svg
            d3.select("#graph").selectAll("*").remove();

            // Continue with D3.js visualization
            let svg = d3.select("#graph").append("svg")
                .attr("width", width)
                .attr("height", height);

            let simulation = d3.forceSimulation()
                .nodes(vertices)
                .force("link", d3.forceLink().links(edges))
                .force("charge", d3.forceManyBody())
                .force("center", d3.forceCenter(width / 2, height / 2));

            let link = svg.append("g")
                .attr("class", "links")
                .selectAll("line")
                .data(edges)
                .enter()
                .append("line");

            let node = svg.append("g")
                .attr("class", "nodes")
                .selectAll("circle")
                .data(vertices)
                .enter()
                .append("circle")
                .attr("r", 5);

            simulation
                .nodes(vertices)
                .on("tick", ticked);

            simulation.force("link")
                .links(edges);

            function ticked() {
                link
                    .attr("x1", function (d) {
                        return d.source.x;
                    })
                    .attr("y1", function (d) {
                        return d.source.y;
                    })
                    .attr("x2", function (d) {
                        return d.target.x;
                    })
                    .attr("y2", function (d) {
                        return d.target.y;
                    });

                node
                    .attr("cx", function (d) {
                        return d.x;
                    })
                    .attr("cy", function (d) {
                        return d.y;
                    });
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

// Call the function with example parameters
fetchGraphData(10, 0.5);
