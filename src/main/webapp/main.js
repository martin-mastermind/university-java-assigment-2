const api = async (method, data) => {
	return await fetch('/KR2/ApiController', {
		method,
		headers: {
			'Content-Type': 'application/json'
    	},
    	body: JSON.stringify(data)
	});
}

document.querySelectorAll('.remove').forEach(btn => {
	btn.addEventListener('click', async(e) => {
		const item = e.currentTarget.closest('[data-id]');
		const item_id = item.dataset.id;
		
		if(!item_id || item_id <= 0) return;
		
		const response = await api("DELETE", {
			id: item_id
		});
		
		if(response.status !== 200) { 
			alert("Не удалось удалить строку");
			return;
		}
		
		location.reload();
	});
});

const modal = document.getElementById('row_modal');
const form = document.getElementById('user_form');

const form_inputs = {
	action: form.querySelector('[name="action"]'),
	id: form.querySelector('[name="id"]'),
	second_name: form.querySelector('[name="second_name"]'),
	first_name: form.querySelector('[name="first_name"]'),
	address: form.querySelector('[name="address"]'),
	phone: form.querySelector('[name="phone"]'),
}

document.querySelector('.add').addEventListener('click', () => {
	flush_form();
	
	form_inputs.action.value = "POST";
	modal.classList.add('opened');
});

document.querySelectorAll('.edit').forEach(btn => {
	btn.addEventListener('click', e => {
		const item = e.currentTarget.closest('[data-id]');
		
		form_inputs.action.value = "PUT";
		form_inputs.id.value = item.dataset.id;
		form_inputs.second_name.value = item.querySelector('.item_sname').innerHTML;
		form_inputs.first_name.value = item.querySelector('.item_fname').innerHTML;
		form_inputs.address.value = item.querySelector('.item_address').innerHTML;
		form_inputs.phone.value = item.querySelector('.item_phone').innerHTML;
		
		modal.classList.add('opened');
	})
});

const flush_form = () => {
	Object.values(form_inputs).forEach(item => {
		item.value = '';
	});
}

document.querySelector('.close').addEventListener('click', () => {
	flush_form();
	modal.classList.remove('opened');
});

window.addEventListener('click', e => {
	if(e.target == modal) {
		flush_form();
		modal.classList.remove('opened');
	}
});

form.addEventListener('submit', async (e) => {
	e.preventDefault();
	
	const response = await api(form_inputs.action.value, {
		id: form_inputs.id.value,
		second_name: form_inputs.second_name.value,
		first_name: form_inputs.first_name.value,
		address: form_inputs.address.value,
		phone: form_inputs.phone.value
	});
		
	if(response.status !== 200) { 
		alert(`Запрос завершился с ошибкой. Код ошибки: ${response.status}`);
		return;
	}
		
	location.reload();
})