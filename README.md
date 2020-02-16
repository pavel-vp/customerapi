# customerapi
Sample REST SpringBoot application 

1. Data Model

-- Customer table
CREATE TABLE customers (
id BIGINT NOT NULL AUTO_INCREMENT,
name varchar(255),
status varchar(255),
createTime BIGINT,
contactInfo varchar(255),
primary key (id)
);
-- Notes by customers
CREATE TABLE notes (
id   BIGINT NOT NULL AUTO_INCREMENT,
customerId  BIGINT,
createTime BIGINT,
data varchar(255),
primary key (id)
);

2. Sample data will be installed when application starts.

3. REST API

GET /customer?field=name&value=Bob&sortby=createTime&sortdir=desc
Retreiving a list of Customers, with filtering by specefied field and with sorting.
All query parameters could be omitted.

GET /customer/1
Retreive customer detail information by customerId.

PUT /customer/status
RequestBody - {"customerId": "1", "newStatus": "ACTIVE"}
Set new status of the given customer.
Returns - {"success" : (true/false), "message": (error message)}

GET /customer/1/notes
Returns all notes by this customerId.

POST /customer/addnote
RequestBody - {"customerId": 1, "note": {"data":"note content"}}
Adding the note to specified customer.

PUT /customer/updatenote
RequestBody - {"customerId": 1, "note": {"id": "1", "data":"note content"}}
Replacing the specefied note with new data.

DELETE /customer/deletenote
RequestBody - {"customerId": 1, "note": {"id": "1"}}
Removing the specefied note.

4. Build the application
./mvn clean install

5. TODOs
With lack of time, there so many things left to do on this task:
  - Unit tests. I did only the integration test, with covering some API's calling.
  - Using JPA for all CRUD operation for the simplicity.
  - Reimplement filtering functionality with more complex filtering rules (ex. equals, less, more, or like). Use many fields in the request
  





