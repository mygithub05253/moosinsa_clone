// pages/ItemDetail.js
import React, { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';

const ItemDetail = () => {
    const { itemId } = useParams();
    const [item, setItem] = useState(null);
    const [sizes, setSizes] = useState([]);   // ★ 사이즈는 별도 API로 가져옴
    const [loading, setLoading] = useState(true);
    const [err, setErr] = useState(null);

    useEffect(() => {
        const controller = new AbortController();
        setLoading(true);
        setErr(null);

        const itemReq  = axios.get(`/api/items/${itemId}`, { signal: controller.signal });
        const sizesReq = axios.get(`/api/itemSizes/byItem/${itemId}`, { signal: controller.signal });
        // ↑ 만약 쿼리파라미터 방식이면:
        // const sizesReq = axios.get(`/api/itemSizes`, { params: { itemId }, signal: controller.signal });

        Promise.allSettled([itemReq, sizesReq])
            .then(([itemRes, sizeRes]) => {
                if (itemRes.status === 'fulfilled') {
                    setItem(itemRes.value.data);
                } else {
                    throw itemRes.reason;
                }

                if (sizeRes.status === 'fulfilled') {
                    const list = Array.isArray(sizeRes.value.data) ? sizeRes.value.data : [];
                    setSizes(list);
                } else {
                    // 사이즈 API가 아직 없거나 실패해도, 상세는 보여주고 사이즈만 빈 배열로 둠
                    console.warn('Failed to fetch sizes:', sizeRes.reason);
                    setSizes([]);
                }
            })
            .catch((e) => {
                if (e?.name !== 'CanceledError') setErr(e);
            })
            .finally(() => setLoading(false));

        return () => controller.abort();
    }, [itemId]);

    if (loading) return <div>로딩 중…</div>;
    if (err) return <div>문제가 발생했어요: {err.message}</div>;
    if (!item) return <div>상품을 찾을 수 없습니다.</div>;

    const formatPrice = (n) =>
        typeof n === 'number' ? n.toLocaleString('ko-KR') + '원' : n ?? '-';

    return (
        <div style={{ maxWidth: 960, margin: '0 auto', padding: 16 }}>
            <Link to="/items">← 목록으로</Link>

            <h1 style={{ marginTop: 12 }}>{item.itemName}</h1>
            <p style={{ color: '#666' }}>{item.itemBrand}</p>

            <div style={{ fontSize: 18, fontWeight: 600, marginBottom: 8 }}>
                {formatPrice(item.itemPrice)}
            </div>

            <div style={{ whiteSpace: 'pre-wrap', lineHeight: 1.6 }}>
                {item.itemDescription || '상품 설명이 없습니다.'}
            </div>

            {/* 옵션 영역 */}
            <div style={{ marginTop: 16, display: 'flex', gap: 24 }}>
                <div>
                    <div style={{ fontWeight: 600, marginBottom: 6 }}>사이즈</div>
                    <div style={{ display: 'flex', gap: 8, flexWrap: 'wrap' }}>
                        {sizes.length > 0 ? (
                            sizes.map((s) => (
                                <span
                                    key={s.itemSizeId ?? `${s.itemSizeName}-${s.itemSizeDescription ?? ''}`}
                                    style={{
                                        border: '1px solid #ddd',
                                        borderRadius: 8,
                                        padding: '6px 10px',
                                        fontSize: 13,
                                        background: '#fafafa',
                                    }}
                                    title={s.itemSizeDescription || ''}
                                >
                  {s.itemSizeName}
                </span>
                            ))
                        ) : (
                            <span style={{ color: '#999' }}>등록된 사이즈가 없습니다.</span>
                        )}
                    </div>
                </div>

                {/* 컬러도 같은 방식으로 별도 API가 있다면 아래처럼 추가 가능
        <div>
          <div style={{ fontWeight: 600, marginBottom: 6 }}>컬러</div>
          <div style={{ display: 'flex', gap: 8, flexWrap: 'wrap' }}>
            {colors.length > 0 ? (
              colors.map((c) => (
                <span key={c.itemColorId} style={{ ... }}>
                  {c.itemColorName}
                </span>
              ))
            ) : (
              <span style={{ color: '#999' }}>등록된 컬러가 없습니다.</span>
            )}
          </div>
        </div>
        */}
            </div>

            {/* 사이즈 추가 화면으로 이동(관리용) */}
            <Link to={`/items/${itemId}/sizes`}>
                <button
                    type="button"
                    style={{ marginTop: 20, padding: '10px 16px', borderRadius: 8, border: '1px solid #222' }}
                >
                    사이즈 추가/수정
                </button>
            </Link>
        </div>
    );
};

export default ItemDetail;
