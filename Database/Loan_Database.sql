CREATE DATABASE loan
go

use loan
go



create table tbRole(
	[roleID] tinyint primary key not null,
	[description] nvarchar(15) not null,

)
go

create table tbUser(
	[user] varchar(16) not null primary key,
	[password] varchar(20) default '123456',
	[role] tinyint not null references tbRole(roleID),
	[fullname] nvarchar(300) not null,
)
go

create table tbLoanType(
	[loanName] nvarchar(20) not null primary key,
	[interestRate] tinyint not null check([interestRate]>0),
)
go

create table tbFine(
	[fineID] tinyint not null primary key,
	[from] int not null,
	[to] int not null,
	[interestRate] tinyint not null,
)
go
create table tbGrade(
	[gradeID] varchar(10) not null primary key,
	[salary] money not null,
	[collateral] money not null,
	[maxAmount] money not null,
	[minPeriod] int not null,
	[maxPeriod] int not null,
)
go
select * from tbGrade



create table tbCustomerDetails(
	[accountNo] int identity(1,1) not null primary key,
	[name] nvarchar(30) not null,
	[indentifyCard] varchar(15) not null unique,
	[Dob] date not null,
	[address] nvarchar(50) not null,
	[organization] nvarchar(50) not null,
	[phone] varchar(11) not null,
	[grossSalary] int not null,
)
go

create table tbContract(
	[contractID] int identity(1,1) not null primary key,
	[accountNo] int not null foreign key references tbCustomerDetails(accountNo),
	[loanType] nvarchar(20) not null foreign key references tbLoanType(loanName),
	[loanAmount] money not null,
	[collateral] money not null,
	[grade] char(1) not null foreign key references tbGrade(gradeID),
	[period] tinyint not null,
	[date] date not null default getdate(),
	[status] nvarchar(10) null,
	[loanStatus] nvarchar(15) null
)
go

create table tbPaymentDetails(
	[contractID] int not null foreign key references tbContract(contractID),
	[month] tinyint not null,
	[principal] money not null,
	[interest] money not null,
	[total] money not null,
	[remaining] money not null,
	[bgDate] date not null,
	[paymentDate] date not null,
	[currentDate] date default getdate(),
	[paymentStatus] nvarchar(10)  default 'Not Yet',
	Constraint [PK_DetailsPayment] primary key (contractID,[month]),
	[indentifyCard] varchar(15) not null ,
	[fine] money not null default 0
)
go



create proc uspSetCurDate
as
begin
	update tbPaymentDetails set [currentDate]=getdate()
end
go

exec uspSetCurDate
go

create proc uspSetStatus
as
begin
	update tbPaymentDetails set [paymentStatus]='Overdue', [fine] =[interest]* (select [interestRate] from tbFine where [interest]> [from] and [interest]<[to])/100 where [currentDate]>[paymentDate] and [paymentStatus] = 'Not Yet'
	update t1 set [status] = 'Overdue' 
			from tbContract as t1
			join tbPaymentDetails as t2
			on t1.contractID= t2.contractID
			where t2.paymentStatus='Overdue'
end
go

exec uspSetStatus
go

select * from tbContract as t1 join tbPaymentDetails as t2 on t1.contractID= t2.contractID	where t2.paymentStatus='Overdue'

select sum(loanAmount) from tbContract

group by accountNo 
having accountNo=6
go


create view tbDetails As
select t1.accountNo, t1.contractID,t2.name,t2.indentifyCard,t1.grade,t1.loanAmount,t1.status, t1.period, t1.date from tbContract t1 join tbCustomerDetails t2
on t1.accountNo = t2.accountNo;
go




create view tbCuslist As
SELECT t3.contractID, t4.name, t4.indentifyCard, t3.loanName, t3.interestRate, t3.loanAmount, t3.period, t3.date, t3.loanStatus
	FROM (SELECT t1.contractID, t1.accountNo, t1.loanType, t1.loanAmount, t1.collateral, t1.grade, t1.period, t1.date, t1.status, t1.loanStatus, t2.loanName, t2.interestRate 
		FROM dbo.tbContract AS t1 INNER JOIN dbo.tbLoanType AS t2 ON t1.loanType = t2.loanName) AS t3 INNER JOIN dbo.tbCustomerDetails AS t4 ON t3.accountNo = t4.accountNo
go


create table tbRequest( [requestID] int identity(1,1) not null primary key, [name] nvarchar(30) not null, [indentifyCard] nvarchar(15) not null , [Dob] date not null, [address] nvarchar(50) not null, [organization] nvarchar(50) not null, [phone] nvarchar(11) not null, [grossSalary] int not null, [loanType] nvarchar(20) not null foreign key references tbLoanType(loanName), [loanAmount] money not null, [collateral] money not null, [grade] char(1) not null foreign key references tbGrade(gradeID), [period] tinyint not null, [date] date not null default getdate(), [status] nvarchar(10) null, [loanStatus] nvarchar(15) null, [irate] int null ) 
go

select sum(principal) from tbPaymentDetails group by indentifyCard,paymentStatus having indentifyCard='212313321' and paymentStatus<>'Paid'