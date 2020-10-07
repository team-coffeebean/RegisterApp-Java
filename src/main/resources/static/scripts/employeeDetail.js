let hideEmployeeSavedAlertTimer = undefined;

document.addEventListener("DOMContentLoaded", () => {
	// TODO: Things that need doing when the view is loaded
	document.getElementById("saveBtn").addEventListener("click", saveActionClick);
});

// Save
function saveActionClick(event) {
	// TODO: Actually save the employee via an AJAX call
	errorMessage = validateEmpInfo()
	if (errorMessage == "") {
		employeeId = document.getElementById("employeeId").value;
		classificationCode = document.getElementById("employeeType").value;
		if (classificationCode == null) {
			classificationCode = -1;
		}
		console.log("here 4");

		const employeeObject = {
			isActive : true,
			id : employeeId,
			employeeId : document.getElementById("employeeEmployeeId").value,
			classification : classificationCode,
			// isInitialEmployee
			// managerId = 
			lastName : document.getElementById("lastName").value,
			firstName : document.getElementById("firstName").value,
			password : document.getElementById("password").value,
		};
		if (employeeId == "") {
			ajaxPost("/api/employee/", employeeObject, (callbackResponse) => {
				if (isSuccessResponse(callbackResponse)) {
					document.getElementById("toggleHidden").classList.remove("hidden");
				}
			});
		} else {
			ajaxPatch("/api/employee/" + employeeId, employeeObject, (callbackResponse) => {
				if (isSuccessResponse(callbackResponse)) {
					document.getElementById("toggleHidden").classList.remove("hidden");
					document.getElementById("employeeEmployeeId").value = callbackResponse.data.employeeId;
				}
			});
		}
	} else {
		document.getElementById("errorValidation").innerHTML = errorMessage;
		return;
	}
	displayEmployeeSavedAlertModal();
}

function validateEmpInfo() {
	var invalidElem = "";
	firstName = document.getElementById("firstName");
	lastName = document.getElementById("lastName");
	password = document.getElementById("password");
	confirm = document.getElementById("verifyPassword");
	empType = document.getElementById("employeeType");
	if (firstName.value == "") {
		invalidElem += "First Name / ";
		firstName.focus();
	}
	if (lastName.value == "") {
		invalidElem += "Last Name /";
		lastName.focus();
	}
	if (password.value == "") {
		invalidElem += "Password /";
		password.focus()
	}
	if (empType.value == "") {
		invalidElem += "Element Type /";
		empType.focus();
	}
	if (password.value != confirm.value) {
		invalideElem += "Password and Confirm Password don't match."
		confirm.focus();
	}
	return invalidElem;
}

function displayEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer) {
		clearTimeout(hideEmployeeSavedAlertTimer);
	}

	const savedAlertModalElement = getSavedAlertModalElement();
	savedAlertModalElement.style.display = "none";
	savedAlertModalElement.style.display = "block";

	hideEmployeeSavedAlertTimer = setTimeout(hideEmployeeSavedAlertModal, 1200);
}

function hideEmployeeSavedAlertModal() {
	if (hideEmployeeSavedAlertTimer) {
		clearTimeout(hideEmployeeSavedAlertTimer);
	}

	getSavedAlertModalElement().style.display = "none";
}
// End save

//Getters and setters
function getSavedAlertModalElement() {
	return document.getElementById("employeeSavedAlertModal");
}
//End getters and setters
