CREATE DATABASE Hospi_data;
USE Hospi_Data;

CREATE TABLE Patients(patient_id VARCHAR(15) PRIMARY KEY, patient_name VARCHAR(50),age INT,gender VARCHAR(10));
CREATE TABLE Doctors(doctor_id VARCHAR(15) PRIMARY KEY, doctor_name VARCHAR(40), specialization VARCHAR(50));
CREATE TABLE Appointments(appointment_id VARCHAR(20), patient_id VARCHAR(15), doctor_id VARCHAR(15), appointment_date DATE);
CREATE TABLE Treatments(treatment_id VARCHAR(20), patient_id VARCHAR(15), diagnosis VARCHAR(20),cost INT);

-- Query 1: Count total appointments handled by each doctor
SELECT d.doctor_id,d.doctor_name,d.specialization,COUNT(a.appointment_id) AS total_appointments
FROM Appointments a JOIN Doctors d
ON a.doctor_id=d.doctor_id
GROUP BY d.doctor_id,d.doctor_name,d.specialization
order by total_appointments DESC;

ALTER TABLE Treatments ADD treatment_date DATE;

-- Query 2: Calculate monthly revenue from treatments
SELECT 
MONTHNAME(treatment_date) AS Month,
YEAR(treatment_date) AS Year,
SUM(cost) AS Monthly_revenue
FROM Treatments
GROUP BY Year, Month, MONTH(treatment_date)
ORDER BY Year DESC, MONTH(treatment_date) DESC 
LIMIT 0, 1000;

-- Query 3: Find most common diseases
SELECT diagnosis,count( DISTINCT treatment_id) AS most_common_diseases_count FROM Treatments 
GROUP BY diagnosis 
ORDER BY most_common_diseases_count DESC;

-- Query 4: Find how frequently each patient visits hospital
SELECT p.patient_id,p.patient_name,COUNT(p.patient_id) AS Patient_visitFrequency 
FROM Patients p JOIN Appointments a
ON p.patient_id=a.patient_id GROUP BY p.patient_id,p.patient_name
ORDER BY Patient_visitFrequency DESC;

-- Query 5: Doctor performance analysis
SELECT d.doctor_id,d.doctor_name,d.specialization,COUNT(DISTINCT a.Appointment_id) AS Total_appointments,
COUNT(DISTINCT a.patient_id) AS unique_patients,
SUM(t.cost) AS total_revenue
FROM doctors d
LEFT JOIN Appointments a ON d.doctor_id=a.doctor_id
LEFT JOIN Treatments t ON a.patient_id=t.patient_id
GROUP BY d.doctor_id,d.doctor_name,d.specialization
ORDER BY total_revenue DESC;

