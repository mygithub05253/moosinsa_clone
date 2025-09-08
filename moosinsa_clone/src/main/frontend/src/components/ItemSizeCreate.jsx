// pages/ItemSizeCreate.jsx
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams, Link } from 'react-router-dom';

export default function ItemSizeCreate() {
    const navigate = useNavigate();
    const { itemId } = useParams();

    const [sizes, setSizes] = useState([{ name: '', description: '' }]);
    const [replace, setReplace] = useState(true); // true: 기존 전체 교체, false: 기존에 추가
    const [submitting, setSubmitting] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    const update = (idx, field, val) => {
        setSizes((list) => {
            const copy = [...list];
            copy[idx] = { ...copy[idx], [field]: val };
            return copy;
        });
    };

    const addRow = () => setSizes((list) => [...list, { name: '', description: '' }]);
    const removeRow = (idx) => setSizes((list) => (list.length === 1 ? list : list.filter((_, i) => i !== idx)));

    const onSubmit = async (e) => {
        e.preventDefault();
        setErrorMsg('');
        setSubmitting(true);
        try {
            const clean = sizes
                .map(s => ({ name: s.name?.trim() || '', description: s.description?.trim() || '' }))
                .filter(s => s.name.length > 0);

            if (clean.length === 0) {
                setErrorMsg('최소 1개 이상의 사이즈를 입력하세요.');
                setSubmitting(false);
                return;
            }

            // 1순위: 벌크 저장
            try {
                await axios.post('/api/itemSizes/bulk-create', {
                    itemId: Number(itemId),
                    replace,
                    sizes: clean.map(s => ({
                        itemSizeName: s.name,
                        itemSizeDescription: s.description || null,
                    })),
                });
            } catch (bulkErr) {
                // 2순위(백엔드에 bulk가 아직 없다면): 단건 API로 대체 수행
                const calls = clean.map(s =>
                    axios.post('/api/itemSizes/create', {
                        itemId: Number(itemId),
                        itemSizeName: s.name,
                        itemSizeDescription: s.description || null,
                    })
                );
                await Promise.all(calls);
            }

            // 완료 후 이동 (원하는 경로로 변경 가능: 상세 페이지가 있다면 `/items/${itemId}`)
            navigate('/items');
        } catch (err) {
            const msg =
                err?.response?.data?.message ||
                err?.response?.data?.error ||
                err?.message || '사이즈 저장 중 오류가 발생했습니다.';
            setErrorMsg(msg);
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div style={{ maxWidth: 720, margin: '0 auto' }}>
            <h2>사이즈 등록 (2/2)</h2>
            <p style={{ marginTop: -8, color: '#666' }}>
                상품 ID:&nbsp;<strong>{itemId}</strong>
            </p>
            {errorMsg && <div style={{ color: 'crimson', marginBottom: 12 }}>{errorMsg}</div>}

            <form onSubmit={onSubmit}>
                <div style={{ display: 'flex', alignItems: 'center', gap: 8, marginBottom: 8 }}>
                    <h3 style={{ margin: 0 }}>사이즈 목록</h3>
                    <button type="button" onClick={addRow} style={{ padding: '6px 10px' }}>
                        + 추가
                    </button>
                </div>

                {sizes.map((s, idx) => (
                    <div key={idx} style={{ display: 'grid', gridTemplateColumns: '2fr 3fr auto', gap: 8, marginTop: 8 }}>
                        <input
                            type="text"
                            placeholder="사이즈명 (예: S/M/L, 270 등)"
                            value={s.name}
                            onChange={(e) => update(idx, 'name', e.target.value)}
                            style={{ padding: 8 }}
                        />
                        <input
                            type="text"
                            placeholder="설명(선택)"
                            value={s.description}
                            onChange={(e) => update(idx, 'description', e.target.value)}
                            style={{ padding: 8 }}
                        />
                        <button
                            type="button"
                            onClick={() => removeRow(idx)}
                            disabled={sizes.length === 1}
                            style={{ padding: '6px 10px' }}
                        >
                            삭제
                        </button>
                    </div>
                ))}

                <div style={{ marginTop: 12, marginBottom: 16 }}>
                    <label>
                        <input
                            type="checkbox"
                            checked={replace}
                            onChange={(e) => setReplace(e.target.checked)}
                        />
                        &nbsp;기존 사이즈 전체 교체 (체크 해제 시 기존에 추가)
                    </label>
                </div>

                <div style={{ display: 'flex', gap: 8 }}>
                    <button type="submit" disabled={submitting} style={{ padding: '8px 12px' }}>
                        {submitting ? '저장 중…' : '저장'}
                    </button>
                    <Link to="/items">
                        <button type="button" style={{ padding: '8px 12px' }}>
                            취소
                        </button>
                    </Link>
                </div>
            </form>
        </div>
    );
}
