# Neo4j with Docker

```bash
docker pull neo4j:latest
```

```bash
docker run --name neo4j-container \
  -p 7474:7474 \       # HTTP port for Neo4j Browser
  -p 7687:7687 \       # Bolt port for Neo4j
  -e NEO4J_AUTH=neo4j/admin123 \  
  neo4j:latest         
```


In docker go to files and change neo4j.conf by enabling line:

``dbms.security.auth_enabled=false``

# Important Links & Info

For JSON Web Tokens testing [JWT](https://jwt.io/)

For Chrome without CORS errors [CORS](https://medium.com/@beligh.hamdi/run-chrome-browser-without-cors-872747142c61)
