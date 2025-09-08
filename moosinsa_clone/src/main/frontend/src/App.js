// App.js
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Items from './Items';
import ItemDetail from './components/ItemDetail';
import ItemCreate from './components/ItemCreate';
import ItemSizeCreate from "./components/ItemSizeCreate";

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                {/* 리스트 */}
                <Route path="/items" element={<Items />} />
                {/* 상세 */}
                <Route path="/items/:itemId" element={<ItemDetail />} />
                {/* 루트로 오면 /items로 */}
                <Route path="/" element={<Navigate to="/items" replace />} />
                <Route path="/items/create" element={<ItemCreate />} />
                <Route path="/items/create-sizes/:itemId" element={<ItemSizeCreate />} />
            </Routes>
        </BrowserRouter>
    );
}
