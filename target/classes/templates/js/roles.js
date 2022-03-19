let items = RolesNameList,
    ul = document.createElement('ul');

document.getElementById('myItemList').appendChild(ul);

items.forEach(function (item) {
    let li = document.createElement('li');
    ul.appendChild(li);

    li.innerHTML += item;
});