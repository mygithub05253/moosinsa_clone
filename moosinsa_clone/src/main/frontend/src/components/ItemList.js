// components/ItemList.js
import React from 'react';
import { Link } from 'react-router-dom';

const ItemList = ({ items }) => {
    return (
        <div>
            {items.map((item) => (
                <Link key={item.itemId} to={`/items/${item.itemId}`} style={{textDecoration: 'none', color: 'inherit'}}>
                    <div>
                        {item.itemName} <br/>
                        {item.itemBrand} <br/>
                        <hr/>
                    </div>
                </Link>
            ))}
            <div style={{display: 'flex', justifyContent: 'flex-end', marginBottom: 12}}>
                <Link to="/items/create" style={{textDecoration: 'none'}}>
                    <button type="button" style={{padding: '8px 12px', cursor: 'pointer'}}>
                        상품 등록
                    </button>
                </Link>
            </div>
        </div>
    );
};

export default ItemList;
