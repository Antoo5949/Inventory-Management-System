use Inventory
go;

select Supplier.Name,Products.Product_Name, Products.Product_Price,Products.Product_Quantity
from Supplier inner join Products on Supplier.Supplier_Id=Products.Supplier_id;
--Category wise sum of Product
select Category.Category_Name,SUM(Products.Product_Quantity)as Quantity
From Category inner join Products
on Category.Category_id=Products.Category_id
group by Category.Category_Name
having Category.Category_Name='Fruits' ;

--Category Wise Supplier Name
select Supplier.Name, Category.Category_Name
from Supplier inner join Products on Supplier.Supplier_Id=Products.Supplier_id
inner join Category on Products.Category_id=Category.Category_id;


--Bulbul kon categoryer food supply dei
select Supplier.Name, Category.Category_Name
from Supplier inner join Products on Supplier.Supplier_Id=Products.Supplier_id
inner join Category on Products.Category_id=Category.Category_id
Where Supplier.Name='Bulbul Ahmed';

SELECT     Products.Product_Name, Category.Category_Name, .Supplier.Name
FROM         Category INNER JOIN
                      Products ON dbo.Category.Category_id = Products.Category_id INNER JOIN
                      Supplier ON dbo.Products.Supplier_id = Supplier.Supplier_Id
                      where Supplier.Name='Bulbul Ahmed';

--Bulbul kon kon product dei
select Supplier.Name, Products.Product_Name
from Supplier inner join Products on Supplier.Supplier_Id=Products.Supplier_id
Where Supplier.Name='Bulbul Ahmed';

Select Products.Product_Name from Products where Products.Product_Quantity in(select Min (Products.Product_Quantity) as Quantity
From Category inner join Products
on Category.Category_id=Products.Category_id
group by Category.Category_Name);

Select Products.Product_Name,Products.Category_id from Products where Products.Product_Quantity in (Select View_1.Expr1 From View_1);

select Min (Products.Product_Quantity) as Quantity,Category.Category_id
From Category inner join Products
on Category.Category_id=Products.Category_id
group by Category.Category_id;




select Count(Products.Product_Name), Min(Products.Product_Quantity) , Category.Category_Name
From Products inner join Category
on Category.Category_id=Products.Category_id
group by Category.Category_Name;

Select (Products.Product_Name)as asabc, Category.Category_Name, MIN(Products.Product_Quantity) as Quantity
from Category inner join Products on Category.Category_id=Products.Category_id;

Select * from View_1;
Select * from View_2;



Select View_2.Product_Name, View_2.Product_Quantity From View_2 where (View_2.Product_Quantity in(Select View_1.Expr1 From View_1) and View_2.Category_id in(Select View_1.Category_id From View_1));




Select Customer.Customer_id, Staff.First_Name from Customer inner join Staff on Customer.Staff_id=Staff.Staff_id;



select * from Customer;

 select [order].Order_id, Customer.Customer_id
 From [order] inner join Customer on [order].Customer_id=Customer.Customer_id;
 --Customer Wise Product Name, Total, Unit Price, PAyment Type
 SELECT     Customer.First_Name, [Order].Order_id, Order_Detail.Order_Detail_id, Order_Detail.Quantity, Order_Detail.Unit_Price, Order_Detail.Discount, Order_Detail.Total, 
                      Products.Product_Name, Payment.Payment_Type
FROM         Customer INNER JOIN
                      [Order] ON Customer.Customer_id = [Order].Customer_id INNER JOIN
                      Order_Detail ON [Order].Order_id = Order_Detail.Order_id INNER JOIN
                      Payment ON Order_Detail.Bill_Number = Payment.Bill_Number INNER JOIN
                      Products ON Order_Detail.Product_id = Products.Product_Id;
                      
--Category Wise Product Name , Customer Name                                           
SELECT     Customer.First_Name, dbo.Customer.Customer_id, Products.Product_Name, Category.Category_Name
FROM         [Order] INNER JOIN
                      Customer ON [Order].Customer_id = Customer.Customer_id INNER JOIN
                      Order_Detail ON [Order].Order_id = Order_Detail.Order_id INNER JOIN
                      Products ON Order_Detail.Product_id = Products.Product_Id INNER JOIN
                      Category ON Products.Category_id = Category.Category_id  ;              
--Cateogory wise total number of customer                      
                      SELECT     Category.Category_Name, COUNT(Customer.Customer_id) AS [ Total Customer ]
FROM[Order] INNER JOIN
                      Customer ON [Order].Customer_id = Customer.Customer_id INNER JOIN
                      Order_Detail ON [Order].Order_id = Order_Detail.Order_id INNER JOIN
                      Products ON Order_Detail.Product_id = Products.Product_Id INNER JOIN
                      Category ON Products.Category_id = Category.Category_id
GROUP BY Category.Category_Name  ;  
--Product Wise Total Number of order
SELECT     Products.Product_Name, COUNT(dbo.Customer.Customer_id) AS [Total Number of Order]
FROM[Order] INNER JOIN
                      Customer ON [Order].Customer_id = Customer.Customer_id INNER JOIN
                      Order_Detail ON [Order].Order_id = Order_Detail.Order_id INNER JOIN
                      Products ON Order_Detail.Product_id = Products.Product_Id INNER JOIN
                      Category ON Products.Category_id = Category.Category_id
GROUP BY Products.Product_Name ; 

SELECT Products.Product_Name, Category.Category_Name, Products.Product_Price, Supplier.Name as [Supplier Name]
FROM   Category INNER JOIN
                      Products ON Category.Category_id = Products.Category_id INNER JOIN
                      Supplier ON Products.Supplier_id = Supplier.Supplier_Id;
                      
                      