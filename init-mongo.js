db = db.getSiblingDB('tenant_a_db');
db.createUser({
    user: 'yu71',
    pwd: '53cret',
    roles: [{role: 'readWrite', db: 'tenant_a_db'}]
});

db = db.getSiblingDB('tenant_b_db');
db.createUser({
    user: 'yu71',
    pwd: '53cret',
    roles: [{role: 'readWrite', db: 'tenant_b_db'}]
});
