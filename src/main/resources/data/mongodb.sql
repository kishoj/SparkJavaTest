use mongodbtest

db.professions.save({firstname:"Kishoj", lastname:"Bajracharya", professions:"Software Developer"})
db.professions.save({firstname:"Prasanna", lastname:"Bajracharya", professions:"Oracle DBA"})
db.professions.save({firstname:"Ram", lastname:"Kasula", professions:"Oracle DBA"})
db.professions.save({firstname:"Suraj", lastname:"Maharjan", professions:"Researcher"})

db.professions.find()