Lab3 reflection- Dirty Checking

Dirty checking is a mechanism  where an update happens without calling the update() method. When an entity is loaded the EntityManager takes a snapshot of it and then when 
the transaction is committed. Then, the snapshot taken is compared to the current state of the object, and if there is a difference then an SQL UPDATE is triggered.
