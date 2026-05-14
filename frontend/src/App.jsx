import "./App.css";
import React from "react";
import { useEffect, useState } from "react";
import {
	getEmployee,
	addEmployee,
	deleteEmployee,
	updateEmployee,
} from "./service/EmployeeService";

function App() {
	const [employee, setEmployee] = useState([]);
	const [form, setForm] = useState({
		name: "",
		email: "",
		department: "",
		salary: "",
	});

	const [editId, setEditId] = useState(null);

	//load data
	useEffect(() => {
		loadEmployee();
	}, []);

	const loadEmployee = async () => {
		const res = await getEmployee();
		setEmployee(res.data);
	};

	const handleChange = (e) => {
		setForm({ ...form, [e.target.name]: e.target.value });
	};

	//submit form
	const handleSubmit = async (e) => {
		e.preventDefault();

		if (editId) {
			await updateEmployee(editId, form);
			setEditId(null);
		} else {
			await addEmployee(form);
		}

		setForm({
			name: "",
			email: "",
			department: "",
			salary: "",
		});

		loadEmployee();
	};

	// delete
	const handleDelete = async (id) => {
		await deleteEmployee(id);
		loadEmployee();
	};

	//edit
	const handleEdit = (emp) => {
		setForm(emp);
		setEditId(emp.id);
	};

	return (
		<div style={{ padding: "20px" }}>
			<h2>EMPLOYEE MANAGEMENT</h2>
			{/* //FORM submit */}
			<form onSubmit={handleSubmit}>
				<input
					name="name"
					value={form.name}
					onChange={handleChange}
					placeholder="Name"
					required
				/>
				<input
					name="email"
					value={form.email}
					onChange={handleChange}
					placeholder="Email"
					required
				/>
				<input
					name="department"
					value={form.department}
					onChange={handleChange}
					placeholder="Department"
					required
				/>
				<input
					name="salary"
					value={form.salary}
					onChange={handleChange}
					placeholder="Salary"
					required
				/>

				<button type="submit">{editId ? "Update" : "Add"}</button>
			</form>

			<hr />

			{/* //table */}

			<table border={1} cellPadding={10}>
				<thead>
					<tr>
						<th>Name</th>
						<th>Email</th>
						<th>Department</th>
						<th>Salary</th>
						<th>Action</th>
					</tr>
				</thead>

				<tbody>
					{employee.map((emp) => (
						<tr key={emp.id}>
							<td>{emp.name}</td>
							<td>{emp.email}</td>
							<td>{emp.department}</td>
							<td>{emp.salary}</td>
							<td>
								<button onClick={() => handleEdit(emp)}>Edit</button>
								<button onClick={() => handleDelete(emp.id)}>Delete</button>
							</td>
						</tr>
					))}
				</tbody>
			</table>
		</div>
	);
}

export default App;
