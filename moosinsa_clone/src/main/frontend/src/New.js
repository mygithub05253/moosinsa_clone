// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';
import Items from "./Items";


function NewDiv() {
    return (
        <div style = {{backgroundColor : "beige"}}>
            <Items/>
        </div>
    );
}

export default NewDiv;