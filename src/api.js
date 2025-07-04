const BASE_URL = import.meta.env.VITE_API_BASE || 'https://xyyjfypjlsbg.sealosbja.site';

export async function apiFetch(path, options = {}) {
  const url = BASE_URL + path;
  const defaultHeaders = {};
  if (!(options.body instanceof FormData)) {
    defaultHeaders['Content-Type'] = 'application/json';
  }
  const res = await fetch(url, {
    credentials: 'include',
    ...options,
    headers: {
      ...defaultHeaders,
      ...(options.headers || {})
    }
  });
  let data;
  try {
    data = await res.json();
  } catch (e) {
    throw new Error('接口返回格式错误');
  }
  if (!res.ok || data.code !== 200) {
    throw new Error(data.message || '接口请求失败');
  }
  return data.data;
}

export async function uploadResume(user_id, file) {
  const formData = new FormData();
  formData.append('user_id', user_id);
  formData.append('file', file);
  const res = await fetch(BASE_URL + '/api/resumes/upload', {
    method: 'POST',
    body: formData
  });
  let data;
  try {
    data = await res.json();
  } catch (e) {
    throw new Error('接口返回格式错误');
  }
  if (!res.ok || data.code !== 200) {
    throw new Error(data.message || '简历上传失败');
  }
  return data.data;
} 