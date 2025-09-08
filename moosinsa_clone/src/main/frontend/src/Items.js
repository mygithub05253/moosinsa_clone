import React, {useState, useEffect} from 'react';
import axios from 'axios';
import ItemList from "./components/ItemList";

const Items = () => {
    const [items, setItems] = useState([]);

    useEffect(() => {
        axios.get('/api/items/')
            .then(response => {
                setItems(response.data)
            });
    }, []);

    return (
        <>
            <h1>Items</h1>
                <ItemList items={items}/>
        </>
    );
}

export default Items