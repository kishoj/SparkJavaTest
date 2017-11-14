CREATE (a:Person { first_name: 'Kishoj', last_name: 'Bajracharya'}), (b:Person { first_name: 'Ram', last_name: 'Kasula'}), (c:Person { first_name: 'Prasanna', last_name: 'Bajracharya'}), (d:Person { first_name: 'Suraj', last_name: 'Maharjan'});

MATCH (a:Person),(b:Person)
WHERE a.first_name = 'Kishoj' AND b.first_name = 'Ram'
CREATE (a)-[r:RELTYPE { name: a.first_name + '<->' + b.first_name }]->(b)
RETURN r

MATCH(a:Person {first_name:'Kishoj'})
CREATE(p:Language {name:'Java'})
CREATE(a)-[w:LIKES]->(p)
return w;

MATCH(a:Person {first_name:'Prasanna'})
CREATE(p:Language {name:'PL/SQL'})
CREATE(a)-[w:LIKES]->(p)
return w;

MATCH(a:Person {first_name:'Ram'})
CREATE(p:Language {name:'Oracle PL/SQL'})
CREATE(a)-[w:LIKES]->(p)
return w;

MATCH(a:Person {first_name:'Suraj'})
CREATE(p:Language {name:'Scala'})
CREATE(a)-[w:LIKES]->(p)
return w;

MATCH (p:Person)-[:LIKES]-> (l:Language)
RETURN p.first_name, l.name as working_language;