class IoUtil {
  static classNames(...args) {
    return args.reduce((classes, arg) => {
      if (!arg) return classes;

      const argType = typeof arg;
      if (argType === 'string' || argType === 'number') return classes + ' ' + arg;
      if (Array.isArray(arg)) return classes + ' ' + IoUtil.classNames(...arg);
      if (argType === 'object') return classes + ' ' + Object.keys(arg).filter(key => arg[key]).join(' ');

      return classes;
    }, '').trim();
  }

  static toKebabCase(str) {
    return str
      .replace(/([a-z])([A-Z])/g, '$1-$2')
      .replace(/\s+|_/g, '-')
      .replace(/-+/g, '-')
      .replace(/^-+|-+$/g, '')
      .toLowerCase();
  }

  static getFont(family, size, style, weight, lineHeight) {
    return `${family}; font-size: ${size}px; font-style: ${style}; font-weight: ${weight}; line-height: ${lineHeight};`;
  }
}

class IoMainWebComponents {
  static prefix = 'io';
  static domain = '';
  static imageRoot = '';
  static cssRoot = '';

  /**
   * @param {object} options
   * @param {string} options.vendorDomain
   */
  static init(options) {
    IoMainWebComponents.domain = options.vendorDomain;
    IoMainWebComponents.imageRoot = `${options.vendorDomain}/images/io-main`;
    IoMainWebComponents.cssRoot = `${options.vendorDomain}/css`;

    ioCustomElements.forEach(customElement => {
      const name = IoUtil.toKebabCase(`${IoMainWebComponents.prefix}-${customElement.name}`);
      window.customElements.define(name, customElement);
    });
  }
}

const ioCustomElements = (() => {
  class Semantic {
    static Pretendard =  'font-family: Pretendard, sans-serif';
    static ImwebSans = 'font-family: Pretendard, sans-serif';
    static color = {
      text: '#15181e',
      'text-sub': '#717680',
      'text-on': '#fff',
      'imweb-blue': '#00b9ff',
      'imweb-brown': '#a65900',
      'imweb-green': '#008c00',
      'imweb-mustard': '#fa0',
      'imweb-neon': '#c8ff00',
      'imweb-neon-green': '#00e600',
      'imweb-pink': '#ff50da',
      'imweb-purple': '#a500e6',
      'imweb-whtie': '#fff',
      'imweb-black': '#000',
      'text-primary': '#1a6dff',
      'text-secondary': '#717680',
      'text-critical': '#d34',
      'action-primary': '#1a6dff',
      'action-secondary': '#fff',
      'action-critical': '#fe5868',
      'action-elevated': '#15181e',
      'action-primaryTonal': '#1a6dff1a',
      'action-secondaryTonal': '#7378821a',
      'action-criticalTonal': '#fe58681a',
    };
    static spacing = {
      0: '0',
      1: '4px',
      2: '8px',
      3: '12px',
      4: '16px',
      5: '20px',
      6: '24px',
      7: '28px',
    };
    static typography = {
      'video-162-bold': IoUtil.getFont(Semantic.Pretendard, 162, 'normal', 700, '193px'),
      'video-148-bold': IoUtil.getFont(Semantic.Pretendard, 148, 'normal', 700, '146px'),
      'video-112-bold': IoUtil.getFont(Semantic.Pretendard, 112, 'normal', 700, '134px'),
      'video-110-bold': IoUtil.getFont(Semantic.Pretendard, 110, 'normal', 700, '108px'),
      'video-70-bold': IoUtil.getFont(Semantic.Pretendard, 70, 'normal', 700, '68px'),
      'video-56-bold': IoUtil.getFont(Semantic.Pretendard, 56, 'normal', 700, '80px'),
      'video-48-bold': IoUtil.getFont(Semantic.Pretendard, 48, 'normal', 700, '60px'),
      'video-42-bold': IoUtil.getFont(Semantic.Pretendard, 42, 'normal', 700, '60px'),
      'video-32-bold': IoUtil.getFont(Semantic.Pretendard, 32, 'normal', 700, '40px'),
      'video-30-bold': IoUtil.getFont(Semantic.Pretendard, 30, 'normal', 700, '44px'),
      'video-20-bold': IoUtil.getFont(Semantic.Pretendard, 20, 'normal', 700, '28px'),
      'custom-72-bold': IoUtil.getFont(Semantic.Pretendard, 72, 'normal', 700, '125%'),
      'heading-5xlarge-bold': IoUtil.getFont(Semantic.Pretendard, 48, 'normal', 700, '60px'),
      'heading-4xlarge-bold': IoUtil.getFont(Semantic.Pretendard, 36, 'normal', 700, '45px'),
      'heading-3xlarge-bold': IoUtil.getFont(Semantic.Pretendard, 30, 'normal', 700, '37.5px'),
      'heading-2xlarge-bold': IoUtil.getFont(Semantic.Pretendard, 24, 'normal', 700, '32px'),
      'heading-xlarge-bold': IoUtil.getFont(Semantic.Pretendard, 20, 'normal', 700, '28px'),
      'heading-medium-bold': IoUtil.getFont(Semantic.Pretendard, 16, 'normal', 700, '24px'),
      'body-3xlarge-bold': IoUtil.getFont(Semantic.ImwebSans, 30, 'normal', 600, '37.5px'),
      'body-2xlarge-bold': IoUtil.getFont(Semantic.ImwebSans, 24, 'normal', 600, '32px'),
      'body-xlarge-bold': IoUtil.getFont(Semantic.ImwebSans, 20, 'normal', 600, '28px'),
      'body-large-bold': IoUtil.getFont(Semantic.ImwebSans, 18, 'normal', 600, '28px'),
      'body-medium-bold': IoUtil.getFont(Semantic.ImwebSans, 16, 'normal', 600, '24px'),
      'body-small-bold': IoUtil.getFont(Semantic.ImwebSans, 14, 'normal', 600, '20px'),
      'body-3xlarge': IoUtil.getFont(Semantic.ImwebSans, 30, 'normal', 400, '37.5px'),
      'body-2xlarge': IoUtil.getFont(Semantic.ImwebSans, 24, 'normal', 400, '32px'),
      'body-xlarge': IoUtil.getFont(Semantic.ImwebSans, 20, 'normal', 400, '28px'),
      'body-large': IoUtil.getFont(Semantic.ImwebSans, 28, 'normal', 400, '28px'),
      'body-medium': IoUtil.getFont(Semantic.ImwebSans, 16, 'normal', 400, '24px'),
      'body-small': IoUtil.getFont(Semantic.ImwebSans, 14, 'normal', 400, '20px'),
      'label-large': IoUtil.getFont(Semantic.Pretendard, 18, 'normal', 400, '28px'),
      'label-medium': IoUtil.getFont(Semantic.Pretendard, 16, 'normal', 400, '24px'),
      'label-small': IoUtil.getFont(Semantic.Pretendard, 14, 'normal', 400, '20px'),
    };
  }

  const PROPS = Symbol('props');
  class IoElement extends HTMLElement {
    [PROPS] = {};

    constructor() {
      super();
      this.attachShadow({ mode: 'open' });
    }

    get props() {
      return this[PROPS];
    }

    attributeChangedCallback(attrName, oldValue, newValue) {
      if (oldValue === newValue) return;
      this.setAttribute(attrName, newValue);

      const isRender = this.onUpdate(attrName, oldValue, newValue) !== false;
      if (!isRender) return;

      this.render();
    }

    connectedCallback() {
      this[PROPS] = new Proxy({}, {
        get: (target, prop) => {
          const value = this.getAttribute(IoUtil.toKebabCase(prop));
          if (/^(true|false|null)+$/g.test(value)) return JSON.parse(value);
          return value === '' ? true : value;
        },
        set: (target, prop, value, receiver) => {
          const result = Reflect.set(target, prop, value, receiver);
          this.setAttribute(prop, value);
          return result;
        },
      });

      this.render();
      this.onMount();
    }

    disconnectedCallback() {
      this.onUnmount();
      this.removeEvent();
    }

    /** @return {string} */
    template() {
      throw new Error('template() must be implemented');
    }

    style() {
      return '';
    }

    render() {
      const link = `<link rel="stylesheet" href="${IoMainWebComponents.cssRoot}/io/vars.css">`;
      this.shadowRoot.innerHTML = `${this.template()} ${this.style()} ${link}`.replace(/\s+/g, ' ');
      this.addEvent();
    }

    onMount() {
    }

    onUnmount() {
    }

    onUpdate() {
    }

    addEvent() {
    }

    removeEvent() {
    }
  }

  class MainTypography extends IoElement {
    static observedAttributes = ['variant', 'ellipsis', 'responsive', 'color'];

    responsiveFont(pcFont, mobileFont) {
      return `.${pcFont}.responsive { ${Semantic.typography[mobileFont]} }`;
    }

    template() {
      const tag = this.props.as || 'p';
      return `
        <${tag} class="${IoUtil.classNames(this.props.variant, { ellipsis: this.props.ellipsis, responsive: this.props.responsive })}">
          <slot></slot>
        </>
      `;
    }

    style() {
      const color = `var(--${this.props.color || 'text'})`;
      const colorVariable = Object.entries(Semantic.color).map(([key, value]) => `--${key}: ${value};`).join(' ');
      const font = Object.entries(Semantic.typography).map(([key, value]) => `.${key} { ${value} }`).join(' ');
      const responsiveFont = [
        ['custom-72-bold', 'heading-4xlarge-bold'],
        ['heading-5xlarge-bold', 'heading-2xlarge-bold'],
        ['heading-3xlarge-bold', 'heading-2xlarge-bold'],
        ['heading-medium-bold', 'heading-small-bold'],
        ['body-3xlarge', 'body-large'],
        ['body-2xlarge', 'body-medium'],
        ['body-xlarge', 'body-small'],
        ['body-3xlarge-bold', 'body-large-bold'],
        ['body-2xlarge-bold', 'body-medium-bold'],
        ['body-xlarge-bold', 'body-small-bold'],
        ['body-large-bold', 'body-small-bold'],
        ['label-large', 'label-small']
      ].map(([pcFont, mobileFont]) => this.responsiveFont(pcFont, mobileFont)).join(' ');

      return `
        <style>
            :host { display: block; ${colorVariable} }
            p { margin-block: unset; margin-inline: unset; color: ${color}; word-break: keep-all; transition: color 0.3s ease-in; }
            .ellipsis { overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }
            ${font}

            @media (max-width: 1440px) {
              .video-162-bold.responsive { ${Semantic.typography['video-112-bold']} }
              .video-48-bold.responsive { ${Semantic.typography['video-32-bold']} }
            }
            @media (max-width: 1024px) {
              ${responsiveFont}
              .video-162-bold.responsive { ${Semantic.typography['video-148-bold']} }
              .video-48-bold.responsive { ${Semantic.typography['video-42-bold']} letter-spacing: 0.63px; }
            }
            @media (max-width: 768px) {
              .video-162-bold.responsive { ${Semantic.typography['video-110-bold']} }
              .video-48-bold.responsive { ${Semantic.typography['video-30-bold']} letter-spacing: 0.45px; }
            }
            @media (max-width: 568px) {
              .video-162-bold.responsive { ${Semantic.typography['video-70-bold']} }
              .video-48-bold.responsive { ${Semantic.typography['video-20-bold']} letter-spacing: 0.3px; }
            }
        </style>
      `;
    }
  }

  class MainButton extends IoElement {
    static observedAttributes = ['to'];

    onMount() {
      if (this.props.to) this.addEventListener('click', () => window.location.assign(this.props.to));
    }

    template() {
      const typography = this.props.size === 'large' ? 'body-xlarge-bold' : 'body-large-bold';

      return `
        <button class="${IoUtil.classNames(this.props.variant, this.props.size || 'medium', typography, {
          tonal: this.props.tonal,
          rounded: this.props.rounded,
          responsive: this.props.responsive,
        })}">
          <slot></slot>
        </button>
      `;
    }

    style() {
      const color = `var(--${this.props.variant === 'secondary' ? 'text-secondary' : 'text-on'})`;
      const backgroundColor = `var(--${this.props.tonal ? `action-${this.props.variant}Tonal` : `action-${this.props.variant}`})`;
      const colorVariable = Object.entries(Semantic.color).map(([key, value]) => `--${key}: ${value};`).join(' ');
      const spacingVariable = Object.entries(Semantic.spacing).map(([key, value]) => `--space-${key}: ${value};`).join(' ');

      return `
        <style>
          :host { ${colorVariable} ${spacingVariable} }
          button { border: none; cursor: pointer; color: ${color}; background-color: ${backgroundColor}; }
          button:focus { outline: none; }
          :host([size="medium"]) button, button { padding: var(--space-3) var(--space-5); border-radius: 8px; ${Semantic.typography['body-large-bold']} }
          :host([size="large"]) button { padding: var(--space-4) var(--space-7); border-radius: 12px; ${Semantic.typography['body-xlarge-bold']} }
          .video { background-color: var(--action-elevated); padding: var(--space-4) var(--space-7); border-radius: 12px; ${Semantic.typography['body-xlarge-bold']} }
          @media (max-width: 1024px) {
            .medium.responsive { padding: var(--space-2) var(--space-3); font-size: 14px; font-weight: 400; line-height: 24px; }
            .large.responsive { padding: var(--space-2) var(--space-5); border-radius: 8px; font-size: 14px; line-height: 24px; }
            .video { padding: 18px 42px; font-size: 30px; line-height: 50px; }
          }
          @media (max-width: 768px) {
            .video { padding: var(--space-3) 32px; font-size: 22px; line-height: 38px; }
          }
          @media (max-width: 568px) {
            .video { padding: var(--space-2) var(--space-5); font-size: 14px; line-height: 24px; border-radius: 8px; }
          }
        </style>
      `;
    }
  }

  return [MainTypography, MainButton];
})();
