// pages/ItemCreate.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate, Link } from 'react-router-dom';

const ItemCreate = () => {
    const navigate = useNavigate();

    // 폼 상태
    const [form, setForm] = useState({
        itemName: '',
        itemBrand: '',
        itemDescription: '',
        itemPrice: '',
        itemStatus: '',
        itemStock: '',
        categoryId: '',   // select
        itemImageId: '',  // 추후
    });

    // 선택지 데이터
    const [categories, setCategories] = useState([]);

    // 로딩/에러
    const [loading, setLoading] = useState(false);
    const [submitting, setSubmitting] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    // 공통 변경 핸들러
    const onChange = (e) => {
        const { name, value } = e.target;
        setForm((f) => ({ ...f, [name]: value }));
    };

    // 초기 데이터 로드 (카테고리만)
    useEffect(() => {
        let cancelled = false;
        (async () => {
            setLoading(true);
            setErrorMsg('');
            try {
                const res = await axios.get('/api/categories/');
                if (!cancelled) {
                    setCategories(Array.isArray(res.data) ? res.data : []);
                }
            } catch (err) {
                if (!cancelled) {
                    const msg = err?.response?.data?.message || err?.message || '카테고리 로딩 중 오류가 발생했습니다.';
                    setErrorMsg(msg);
                }
            } finally {
                if (!cancelled) setLoading(false);
            }
        })();
        return () => { cancelled = true; };
    }, []);

    const onSubmit = async (e) => {
        e.preventDefault();
        setErrorMsg('');

        // 필수 검증
        if (!form.itemName.trim() || !form.itemBrand.trim()) {
            setErrorMsg('상품명과 브랜드는 필수입니다.');
            return;
        }
        if (!form.categoryId) {
            setErrorMsg('카테고리를 선택하세요.');
            return;
        }

        setSubmitting(true);
        try {
            // 아이템만 생성
            const payload = {
                itemName: form.itemName,
                itemBrand: form.itemBrand,
                itemDescription: form.itemDescription || null,
                itemPrice:
                    form.itemPrice === '' ? null :
                        Number.isNaN(Number(form.itemPrice)) ? null : Number(form.itemPrice),
                itemStatus: form.itemStatus || null,
                itemStock:
                    form.itemStock === '' ? null :
                        Number.isNaN(Number(form.itemStock)) ? null : Number(form.itemStock),
                categoryId: Number(form.categoryId),
                itemImageId:
                    form.itemImageId === '' ? null :
                        Number.isNaN(Number(form.itemImageId)) ? null : Number(form.itemImageId),
            };

            const res = await axios.post('/api/items/create', payload);
            const itemId = res.data?.itemId ?? res.data?.id;
            if (!itemId) throw new Error('생성된 상품 ID를 찾을 수 없습니다.');

            // 2단계(사이즈 입력) 화면으로 이동
            navigate(`/items/create-sizes/${itemId}`);
        } catch (err) {
            const msg =
                err?.response?.data?.message ||
                err?.response?.data?.error ||
                err?.message ||
                '상품 생성 중 오류가 발생했습니다.';
            setErrorMsg(msg);
        } finally {
            setSubmitting(false);
        }
    };

    return (
        <div style={{ maxWidth: 560, margin: '0 auto' }}>
            <h2>상품 등록 (1/2)</h2>

            {loading && <div style={{ marginBottom: 12 }}>카테고리 로딩 중…</div>}
            {errorMsg && <div style={{ color: 'crimson', marginBottom: 12 }}>{errorMsg}</div>}

            <form onSubmit={onSubmit}>
                {/* 기본 입력 */}
                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="itemName">상품명 *</label>
                    <input
                        id="itemName"
                        name="itemName"
                        type="text"
                        value={form.itemName}
                        onChange={onChange}
                        required
                        style={{ width: '100%', padding: 8 }}
                        placeholder="예) 에어맥스 270"
                    />
                </div>

                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="itemBrand">브랜드 *</label>
                    <input
                        id="itemBrand"
                        name="itemBrand"
                        type="text"
                        value={form.itemBrand}
                        onChange={onChange}
                        required
                        style={{ width: '100%', padding: 8 }}
                        placeholder="예) Nike"
                    />
                </div>

                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="itemDescription">설명</label>
                    <textarea
                        id="itemDescription"
                        name="itemDescription"
                        value={form.itemDescription}
                        onChange={onChange}
                        rows={4}
                        style={{ width: '100%', padding: 8 }}
                        placeholder="상품에 대한 간단 설명"
                    />
                </div>

                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="itemPrice">가격</label>
                    <input
                        id="itemPrice"
                        name="itemPrice"
                        type="number"
                        value={form.itemPrice}
                        onChange={onChange}
                        style={{ width: '100%', padding: 8 }}
                        placeholder="예) 129000"
                        min="0"
                        step="1"
                    />
                </div>

                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="itemStatus">상태</label>
                    <input
                        id="itemStatus"
                        name="itemStatus"
                        type="text"
                        value={form.itemStatus}
                        onChange={onChange}
                        style={{ width: '100%', padding: 8 }}
                        placeholder="예) ACTIVE / INACTIVE 등"
                    />
                </div>

                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="itemStock">재고</label>
                    <input
                        id="itemStock"
                        name="itemStock"
                        type="number"
                        value={form.itemStock}
                        onChange={onChange}
                        style={{ width: '100%', padding: 8 }}
                        placeholder="예) 100"
                        min="0"
                        step="1"
                    />
                </div>

                {/* 카테고리 (선택만) */}
                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="categoryId">카테고리 *</label>
                    <select
                        id="categoryId"
                        name="categoryId"
                        value={form.categoryId}
                        onChange={onChange}
                        required
                        style={{ width: '100%', padding: 8 }}
                    >
                        <option value="">-- 카테고리를 선택하세요 --</option>
                        {(categories ?? []).filter(Boolean).map((c) => (
                            <option key={c.categoryId} value={String(c.categoryId)}>
                                {c.name}
                            </option>
                        ))}
                    </select>
                </div>

                {/* 이미지 ID (보류) */}
                <div style={{ marginBottom: 12 }}>
                    <label htmlFor="itemImageId">이미지 ID (추후)</label>
                    <input
                        id="itemImageId"
                        name="itemImageId"
                        type="text"
                        value={form.itemImageId}
                        onChange={onChange}
                        style={{ width: '100%', padding: 8 }}
                        placeholder="추후 구현을 위해 보류"
                    />
                </div>

                {/* 액션 */}
                <div style={{ display: 'flex', gap: 8 }}>
                    <button type="submit" disabled={submitting} style={{ padding: '8px 12px', cursor: 'pointer' }}>
                        {submitting ? '생성 중…' : '다음(사이즈 입력)'}
                    </button>
                    <Link to="/items">
                        <button type="button" style={{ padding: '8px 12px', cursor: 'pointer' }}>
                            목록으로
                        </button>
                    </Link>
                </div>
            </form>
        </div>
    );
};

export default ItemCreate;
